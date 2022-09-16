package com.jxcia.pt.service;

import com.jxcia.pt.entity.Role;

import java.util.List;

public interface RoleService {

    // 新增
    Boolean insert(String name, String nameZh);

    // 通过name或nameZh判断是否存在同名的角色
    Boolean isExist(String name, String nameZh);

    // 通过id来判断是否存在角色
    Boolean isExist(Integer id);

    // 通过name或者nameZh判断是否存在同名的角色(除开自己)
    Boolean isExist(Integer id, String name, String nameZh);

    // 删除
    Boolean delete(Integer id);

    // 更新
    Boolean update(Integer id, String name, String nameZh);

    // 通过id获取
    Role getById(Integer id);

    // 获取所有角色
    List<Role> getAll(String name, String nameZh);

}
