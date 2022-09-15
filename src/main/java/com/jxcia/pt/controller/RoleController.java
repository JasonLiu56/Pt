package com.jxcia.pt.controller;

import cn.hutool.core.util.ObjectUtil;
import com.jxcia.pt.common.Result;
import com.jxcia.pt.dto.req.*;
import com.jxcia.pt.entity.Role;
import com.jxcia.pt.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@Slf4j
@Api(tags = "角色模块", value = "角色模块", description="角色模块接口")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "新增角色")
    @PostMapping("/insert")
    public Result insert(@RequestBody RoleInsertReq roleInsertReq) throws Exception {
        // 参数检查
        if (StringUtils.isEmpty(roleInsertReq.getName()) || StringUtils.isEmpty(roleInsertReq.getNameZh())) {
            log.error("新增角色参数为空 name:{}, nameZh:{}", roleInsertReq.getName(), roleInsertReq.getNameZh());
            return Result.fail("新增角色为空");
        }

        // 判断已经存在同名的角色
        if (roleService.isExist(roleInsertReq.getName(), roleInsertReq.getNameZh())) {
            log.error("新增角色已经存在 name:{}, nameZh:{}", roleInsertReq.getName(), roleInsertReq.getNameZh());
            return Result.fail("新增角色已经存在");
        }

        // 插入角色
        Boolean flag = roleService.insert(roleInsertReq.getName(), roleInsertReq.getNameZh());

        if (flag) {
            log.info("新增角色成功: {}", roleInsertReq);
            return Result.succ("新增角色成功");
        } else {
            log.error("新增角色失败: {}", roleInsertReq);
            return Result.fail("新增角色失败");
        }
    }

    @ApiOperation(value = "删除角色")
    @PostMapping("/delete")
    public Result delete(@RequestBody RoleDeleteReq roleDeleteReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(roleDeleteReq.getId())) {
            log.error("删除角色参数为空 id:{}", roleDeleteReq.getId());
            return Result.fail("删除角色参数为空");
        }

        // 通过id判断角色是否存在
        if (!roleService.isExist(roleDeleteReq.getId())) {
            log.error("删除角色不存在 id:{}", roleDeleteReq.getId());
            return Result.fail("删除角色不存在");
        }

        // 删除角色
        Boolean flag = roleService.delete(roleDeleteReq.getId());

        if (flag) {
            log.info("删除角色成功: {}", roleDeleteReq);
            return Result.succ("删除角色成功");
        } else {
            log.error("删除角色失败: {}", roleDeleteReq);
            return Result.fail("删除角色失败");
        }
    }

    @ApiOperation(value = "更新角色")
    @PostMapping("/update")
    public Result update(@RequestBody RoleUpdateReq roleUpdateReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(roleUpdateReq.getId()) || StringUtils.isEmpty(roleUpdateReq.getName()) ||
                ObjectUtil.isEmpty(roleUpdateReq.getNameZh())) {
            log.error("更新角色参数为空 id:{}, name:{}, nameZh:{}", roleUpdateReq.getId(), roleUpdateReq.getName(),
                    roleUpdateReq.getNameZh());
            return Result.fail("更新角色参数为空");
        }

        // 通过id判断待修改的角色是否存在
        if(!roleService.isExist(roleUpdateReq.getId())) {
            log.error("待更新角色不存在 id:{}", roleUpdateReq.getId());
            return Result.fail("待更新角色不存在");
        }

        // 更新角色
        Boolean flag = roleService.update(roleUpdateReq.getId(), roleUpdateReq.getName(), roleUpdateReq.getNameZh());

        if (flag) {
            log.info("更新角色成功: {}", roleUpdateReq);
            return Result.succ("更新角色成功");
        } else {
            log.error("更新角色失败: {}", roleUpdateReq);
            return Result.fail("更新角色失败");
        }
    }

    @ApiOperation(value = "通过id获取角色信息")
    @GetMapping("/getById")
    public Result getById(@RequestBody RoleGetByIdReq roleGetByIdReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(roleGetByIdReq.getId())) {
            log.error("查询角色参数为空 id:{}", roleGetByIdReq.getId());
            return Result.fail("查询角色参数为空");
        }

        // 通过id查询
        Role role = roleService.getById(roleGetByIdReq.getId());

        return Result.succ(role);
    }

    @ApiOperation(value = "获取所有角色")
    @GetMapping("/getAll")
    public Result getAll(@RequestBody RoleGetAllReq roleGetAllReq) throws Exception {
        // 参数检查
        if (StringUtils.isEmpty(roleGetAllReq.getName()) || StringUtils.isEmpty(roleGetAllReq.getNameZh())) {
            log.error("查询所有参数为空 name:{} nameZh: {}", roleGetAllReq.getName(), roleGetAllReq.getNameZh());
            return Result.fail("查询所有参数为空");
        }

        // 查询所有
        List<Role> roles = roleService.getAll(roleGetAllReq.getName(), roleGetAllReq.getNameZh());

        return Result.succ(roles);
    }

}
