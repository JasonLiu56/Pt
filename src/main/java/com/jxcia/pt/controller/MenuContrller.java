package com.jxcia.pt.controller;

import com.jxcia.pt.common.Constant;
import com.jxcia.pt.common.Result;
import com.jxcia.pt.dto.req.*;
import com.jxcia.pt.entity.Menu;
import com.jxcia.pt.service.MenuService;
import com.jxcia.pt.service.RoleService;
import com.jxcia.pt.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/menu")
@Slf4j
@Api(tags = "菜单模块", value = "菜单模块", description="菜单模块接口")
public class MenuContrller {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "新增菜单")
    @PostMapping("/insert")
    public Result insert(@RequestBody MenuInsertReq menuInsertReq) throws Exception {
        // 参数检查
        if (StringUtils.isEmpty(menuInsertReq.getPattern()) || ObjectUtils.isEmpty(menuInsertReq.getRoleIds()) || menuInsertReq.getRoleIds().isEmpty()) {
            log.error("新增菜单参数为空 pattern:{} roleIds:{}", menuInsertReq.getPattern(), menuInsertReq.getRoleIds());
            return Result.fail("新增菜单参数为空");
        }

        // 判断路由是否已存在
        if (menuService.isExist(menuInsertReq.getPattern())) {
            log.error("新增菜单已存在 pattern:{} roleIds: {}", menuInsertReq.getPattern(), menuInsertReq.getRoleIds());
            return Result.fail("新增菜单已存在");
        }

        // 判断roleIds是否都存在
        for (Integer roleId : menuInsertReq.getRoleIds()) {
            if (!roleService.isExist(roleId)) {
                log.error("新增菜单角色id不存在 roleId:{}", roleId);
                return Result.fail("新增菜单角色id不存在");
            }
        }

        // 新增菜单
        Boolean flag = menuService.insert(menuInsertReq.getPattern(), menuInsertReq.getRoleIds());

        // 如果redis中存在menu的缓存就删除
        if (flag && redisUtil.hasKey(Constant.MENU_KEY)) {
            redisUtil.del(Constant.MENU_KEY);
            log.info("删除redis中menu缓存");
        }

        if (flag) {
            log.info("新增菜单成功: {}", menuInsertReq);
            return Result.succ("新增菜单成功");
        } else {
            log.error("新增菜单失败: {}", menuInsertReq);
            return Result.fail("新增菜单失败");
        }
    }

    @ApiOperation(value = "删除菜单")
    @PostMapping("/delete")
    public Result delete(@RequestBody MenuDeleteReq menuDeleteReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(menuDeleteReq.getId())) {
            log.error("删除菜单参数为空 id:{}", menuDeleteReq.getId());
            return Result.fail("删除菜单参数为空");
        }

        // 待删除的菜单是否存在
        if (!menuService.isExist(menuDeleteReq.getId())) {
            log.error("待删除的菜单不存在 id:{}", menuDeleteReq.getId());
            return Result.fail("待删除的菜单不存在");
        }

        // 删除菜单
        Boolean flag = menuService.delete(menuDeleteReq.getId());

        // 如果redis中存在menu的缓存就删除
        if (flag && redisUtil.hasKey(Constant.MENU_KEY)) {
            redisUtil.del(Constant.MENU_KEY);
            log.info("删除redis中menu缓存");
        }


        if (flag) {
            log.info("删除菜单成功: {}", menuDeleteReq);
            return Result.succ("删除菜单成功");
        } else {
            log.error("删除菜单失败: {}", menuDeleteReq);
            return Result.fail("删除菜单失败");
        }
    }

    @ApiOperation(value = "更新菜单")
    @PostMapping("/update")
    public Result update(@RequestBody MenuUpdateReq menuUpdateReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(menuUpdateReq.getId()) || StringUtils.isEmpty(menuUpdateReq.getPattern()) || ObjectUtils.isEmpty(menuUpdateReq.getRoleIds())
        || menuUpdateReq.getRoleIds().isEmpty()) {
            log.error("更新菜单参数为空 id:{} pattern: {} roleIds: {}", menuUpdateReq.getId(), menuUpdateReq.getPattern(), menuUpdateReq.getRoleIds());
            return Result.fail("更新菜单参数为空");
        }

        // 检查待更新的菜单是否存在(通过id)
        if (!menuService.isExist(menuUpdateReq.getId())) {
            log.error("待更新菜单不存在 id:{} pattern: {} roleIds: {}", menuUpdateReq.getId(), menuUpdateReq.getPattern(), menuUpdateReq.getRoleIds());
            return Result.fail("待更新菜单不存在");
        }

        // 检查pattern是否存在(除开自己)
        if (menuService.isExist(menuUpdateReq.getId(), menuUpdateReq.getPattern())) {
            log.error("待更新菜单已经存在 id:{} pattern: {} roleIds: {}", menuUpdateReq.getId(), menuUpdateReq.getPattern(), menuUpdateReq.getRoleIds());
            return Result.fail("待更新菜单已经存在");
        }

        // 检查roleId是否存在
        for (Integer roleId : menuUpdateReq.getRoleIds()) {
            if (!roleService.isExist(roleId)) {
                log.error("更新菜单 角色不存在 roleId:{}", roleId);
                return Result.fail("更新菜单 角色不存在");
            }
        }

        // 菜单更新
        Boolean flag = menuService.update(menuUpdateReq.getId(), menuUpdateReq.getPattern(), menuUpdateReq.getRoleIds());

        // 如果redis中存在menu的缓存就删除
        if (flag && redisUtil.hasKey(Constant.MENU_KEY)) {
            redisUtil.del(Constant.MENU_KEY);
            log.info("删除redis中menu缓存");
        }

        if (flag) {
            log.info("更新菜单成功: {}", menuUpdateReq);
            return Result.succ("更新菜单成功");
        } else {
            log.error("更新菜单失败: {}", menuUpdateReq);
            return Result.fail("更新菜单失败");
        }
    }


    @ApiOperation(value = "通过id获取菜单")
    @PostMapping("/getById")
    public Result getById(@RequestBody MenuGetByIdReq menuGetByIdReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(menuGetByIdReq.getId())) {
            log.error("通过id获取菜单参数为空 id:{}", menuGetByIdReq.getId());
            return Result.fail("通过id获取菜单参数为空");
        }

        // 判断id是否存在
        if (!menuService.isExist(menuGetByIdReq.getId())) {
            log.error("通过id获取菜单不存在 id:{}", menuGetByIdReq.getId());
            return Result.fail("通过id获取菜单不存在");
        }

        // 通过id获取菜单
        Menu menu = menuService.getById(menuGetByIdReq.getId());
        log.info("通过id获取菜单 id:{}", menuGetByIdReq.getId());

        return Result.succ(menu);
    }

    @ApiOperation(value = "获取所有菜单")
    @PostMapping("/getAll")
    public Result getAll(@RequestBody MenuGetAllReq menuGetAllReq) throws Exception {
        List<Menu> menus = menuService.getAll(menuGetAllReq.getPattern());
        log.info("获取所有菜单 param:{}", menuGetAllReq);

        return Result.succ(menus);
    }

}
