package com.jxcia.pt.controller;

import com.jxcia.pt.common.Result;
import com.jxcia.pt.dto.req.*;
import com.jxcia.pt.dto.vo.PageVo;
import com.jxcia.pt.entity.User;
import com.jxcia.pt.service.RoleService;
import com.jxcia.pt.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "用户模块", value = "用户模块", description="用户模块接口")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @ApiOperation(value = "新增用户")
    @PostMapping("/insert")
    public Result insert(@RequestBody UserInsertReq userInsertReq) throws Exception {
        // 检查参数
        if (StringUtils.isEmpty(userInsertReq.getStaffNo()) || StringUtils.isEmpty(userInsertReq.getNickname()) || StringUtils.isEmpty(userInsertReq.getPassword())
        || StringUtils.isEmpty(userInsertReq.getRoleIds()) || userInsertReq.getRoleIds().isEmpty()) {
            log.error("新增用户参数为空 username:{} nickname:{} password:{} roleIds:{}", userInsertReq.getStaffNo(), userInsertReq.getNickname(),
                    userInsertReq.getPassword(), userInsertReq.getRoleIds());
            return Result.fail("新增用户参数为空");
        }

        // 检查是否存在同学工号的用户
        if (userService.isExist(userInsertReq.getStaffNo())) {
            log.error("新增用户已经存在 username:{} nickname:{} password:{} roleIds:{}", userInsertReq.getStaffNo(), userInsertReq.getNickname(),
                    userInsertReq.getPassword(), userInsertReq.getRoleIds());
            return Result.fail("新增用户已经存在");
        }

        // 检查角色是否存在
        for (Integer roleId : userInsertReq.getRoleIds()) {
            if (!roleService.isExist(roleId)) {
                log.error("新增用户的角色不存在 username:{} nickname:{} password:{} roleId:{}", userInsertReq.getStaffNo(), userInsertReq.getNickname(),
                        userInsertReq.getPassword(), roleId);
                return Result.fail("新增用户的角色不存在");
            }
        }

        // 解码Base64加密的密码
        String password = new String(Base64.decodeBase64(userInsertReq.getPassword().getBytes()));
        // 加密密码
        String encodedPassword = "{bcrypt}" + passwordEncoder.encode(password);

        // 新增用户
        Boolean flag = userService.insert(userInsertReq.getStaffNo(), encodedPassword, userInsertReq.getNickname(), userInsertReq.getRoleIds());

        if (flag) {
            log.info("新增用户成功: {}", userInsertReq);
            return Result.succ("新增用户成功");
        } else {
            log.error("新增用户失败: {}", userInsertReq);
            return Result.fail("新增用户失败");
        }
    }

    @ApiOperation(value = "删除用户")
    @PostMapping("/delete")
    public Result delete(@RequestBody UserDeleteReq userDeleteReq) throws Exception {
        // 检查参数
        if (ObjectUtils.isEmpty(userDeleteReq.getId())) {
            log.error("删除用户参数为空 id:{}", userDeleteReq.getId());
            return Result.fail("删除用户参数为空");
        }

        // 检查待删除的用户是否存在
        if (!userService.isExist(userDeleteReq.getId())) {
            log.error("待删除的用户不存在 id:{}", userDeleteReq.getId());
            return Result.fail("待删除的用户不存在");
        }

        // 删除用户
        Boolean flag = userService.delete(userDeleteReq.getId());

        if (flag) {
            log.info("删除用户成功: {}", userDeleteReq);
            return Result.succ("删除用户成功");
        } else {
            log.error("删除用户失败: {}", userDeleteReq);
            return Result.fail("删除用户失败");
        }
    }

    @ApiOperation(value = "更新用户昵称")
    @PostMapping("/updateNickname")
    public Result updateNickname(@RequestBody UserUpdateNicknameReq userUpdateNicknameReq) throws Exception  {
        // 检查参数
        if (ObjectUtils.isEmpty(userUpdateNicknameReq.getId()) || StringUtils.isEmpty(userUpdateNicknameReq.getNickname())) {
            log.error("更新用户昵称参数为空 id:{} nickname:{}", userUpdateNicknameReq.getId(), userUpdateNicknameReq.getNickname());
            return Result.fail("更新用户昵称参数为空");
        }

        // 通过id查询是否存在用户
        if (!userService.isExist(userUpdateNicknameReq.getId())) {
            log.error("更新用户昵称用户不存在 id:{} nickname:{}", userUpdateNicknameReq.getId(), userUpdateNicknameReq.getNickname());
            return Result.fail("待更新的用户不存在");
        }

        // 更新用户昵称
        Boolean flag = userService.updateNickname(userUpdateNicknameReq.getId(), userUpdateNicknameReq.getNickname());

        if (flag) {
            log.info("更新用户昵称成功: {}", userUpdateNicknameReq);
            return Result.succ("更新用户昵称成功");
        } else {
            log.error("更新用户昵称失败: {}", userUpdateNicknameReq);
            return Result.fail("更新用户昵称失败");
        }
    }

    @ApiOperation(value = "更新用户密码")
    @PostMapping("/updatePassword")
    public Result updatePassword(@RequestBody UserUpdatePasswordReq userUpdatePasswordReq) throws Exception {
        // 检查参数
        if (ObjectUtils.isEmpty(userUpdatePasswordReq.getId()) || StringUtils.isEmpty(userUpdatePasswordReq.getPassword())) {
            log.error("更新用户密码参数为空 id:{} password:{}", userUpdatePasswordReq.getId(), userUpdatePasswordReq.getPassword());
            return Result.fail("更新用户密码参数为空");
        }

        // 通过id查询是否存在用户
        if (!userService.isExist(userUpdatePasswordReq.getId())) {
            log.error("更新用户密码用户不存在 id:{} nickname:{}", userUpdatePasswordReq.getId(), userUpdatePasswordReq.getPassword());
            return Result.fail("待更新的用户不存在");
        }

        // 解码Base64加密的密码
        String password = new String(Base64.decodeBase64(userUpdatePasswordReq.getPassword().getBytes()));
        // 加密密码
        String encodedPassword = "{bcrypt}" + passwordEncoder.encode(password);

        // 更新用户密码
        Boolean flag = userService.updatePassword(userUpdatePasswordReq.getId(), encodedPassword);

        if (flag) {
            log.info("更新用户密码成功: {}", userUpdatePasswordReq);
            return Result.succ("更新用户密码成功");
        } else {
            log.error("更新用户密码失败: {}", userUpdatePasswordReq);
            return Result.fail("更新用户密码失败");
        }
    }

    @ApiOperation(value = "更新用户角色")
    @PostMapping("/updateRoles")
    public Result updateRoles(@RequestBody UserUpdateRolesReq userUpdateRolesReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(userUpdateRolesReq.getId()) || ObjectUtils.isEmpty(userUpdateRolesReq.getRoleIds()) || userUpdateRolesReq.getRoleIds().isEmpty()) {
            log.error("更新用户角色参数为空 id:{} roleIds:{}", userUpdateRolesReq.getId(), userUpdateRolesReq.getRoleIds());
            return Result.fail("更新用户角色参数为空");
        }

        // 查看用户是否存在
        if (!userService.isExist(userUpdateRolesReq.getId())) {
            log.error("更新用户角色 用户不存在 id:{} roleIds:{}", userUpdateRolesReq.getId(), userUpdateRolesReq.getRoleIds());
            return Result.fail("更新用户角色用户不存在");
        }

        // 查看角色是否存在
        for (Integer roleId : userUpdateRolesReq.getRoleIds()) {
            if (!roleService.isExist(roleId)) {
                log.error("更新用户角色 角色不存在 id:{} roleId:{}", userUpdateRolesReq.getId(), roleId);
                return Result.fail("更新用户角色 角色不存在");
            }
        }

        // 更新用户角色
        Boolean flag = userService.updateRoles(userUpdateRolesReq.getId(), userUpdateRolesReq.getRoleIds());

        if (flag) {
            log.info("更新用户角色成功: {}", userUpdateRolesReq);
            return Result.succ("更新用户角色成功");
        } else {
            log.error("更新用户角色失败: {}", userUpdateRolesReq);
            return Result.fail("更新用户角色失败");
        }
    }

    @ApiOperation(value = "通过id获取用户")
    @PostMapping("/getById")
    public Result getById(@RequestBody UserGetByIdReq userGetByIdReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(userGetByIdReq.getId())) {
            log.error("通过id获取用户参数为空 id:{}", userGetByIdReq.getId());
            return Result.fail("通过id获取用户参数为空");
        }

        // 通过id查询是否存在用户
        if (!userService.isExist(userGetByIdReq.getId())) {
            log.error("通过id获取用户用户不存在 id:{}", userGetByIdReq.getId());
            return Result.fail("待查询的用户不存在");
        }

        // 通过id获取用户
        User user = userService.getById(userGetByIdReq.getId());
        log.info("通过id获取用户 user:{}", user);

        return Result.succ(user);
    }

    @ApiOperation(value = "分页查询用户")
    @PostMapping("/getByPage")
    public Result getByPage(@RequestBody UserGetPageReq userGetPageReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(userGetPageReq.getPageNum()) || ObjectUtils.isEmpty(userGetPageReq.getPageSize())) {
            log.error("分页查询用户参数为空 pageNum:{} pageSize:{} nickname:{}", userGetPageReq.getPageNum(), userGetPageReq.getPageSize(), userGetPageReq.getNickname());
            return Result.fail("分页查询用户参数为空");
        }

        // 分页查询
        List<User> users = userService.getByPage(userGetPageReq.getPageNum(), userGetPageReq.getPageSize(), userGetPageReq.getNickname());
        Integer total = userService.countByPage(userGetPageReq.getNickname());
        log.info("分页查询用户 total:{} users:{}", total, users);

        return Result.succ(new PageVo(total, users));
    }

}
