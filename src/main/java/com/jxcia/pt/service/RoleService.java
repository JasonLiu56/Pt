package com.jxcia.pt.service;

import com.jxcia.pt.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleService {

    // 新增
    Boolean insert(String name, String nameZh);

    // 删除
    Boolean delete(Integer id);

    // 更新
    Boolean update(Integer id, String name, String nameZh);

    // 通过id获取
    Role getById(Integer id);

    // 获取所有角色
    List<Role> getAll(String name, String nameZh);

}
