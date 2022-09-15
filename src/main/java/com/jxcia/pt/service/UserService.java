package com.jxcia.pt.service;

import com.jxcia.pt.entity.User;

import java.util.List;

public interface UserService {

    // 新增
    Boolean insert(String username, String password, String nickname);

    // 删除
    Boolean delete(Integer id);

    // 更新昵称
    Boolean updateNickname(Integer id, String nickname);

    // 更新密码
    Boolean updatePassword(Integer id, String password);

    // 通过id查找
    User findById(Integer id);

    // 通过条件查询分页查询
    List<User> findByPage(String nickname);

}
