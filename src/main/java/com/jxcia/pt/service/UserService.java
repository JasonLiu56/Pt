package com.jxcia.pt.service;

import com.jxcia.pt.entity.User;

import java.util.List;

public interface UserService {

    // 新增
    Boolean insert(String username, String password, String nickname, List<Integer> roleIds);

    // 根据username判断是否存在
    Boolean isExist(String username);

    // 根据id判断是否存在
    Boolean isExist(Integer id);

    // 删除
    Boolean delete(Integer id);

    // 更新昵称
    Boolean updateNickname(Integer id, String nickname);

    // 更新密码
    Boolean updatePassword(Integer id, String password);

    // 修改密码
    Boolean changePassword(String username, String password);

    // 更新用户角色
    Boolean updateRoles(Integer id, List<Integer> roleIds);

    // 通过id查找
    User getById(Integer id);

    // 通过username查找
    User getByUsername(String username);

    // 通过条件查询分页查询
    List<User> getByPage(Integer pageNum, Integer pageSize, String nickname);

    // 统计所有的用户数
    Integer countByPage(String nickname);

}
