package com.jxcia.pt.mapper;

import com.jxcia.pt.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {

    // 新增
    Boolean insert(@Param("name") String name, @Param("nameZh") String nameZh);

    // 判断角色是否存在
    Boolean isExistByNameOrNameZh(@Param("name") String name, @Param("nameZh") String nameZh);

    // 通过id判断角色是否存在
    Boolean isExistById(@Param("id") Integer id);

    // 删除
    Boolean delete(@Param("id") Integer id);

    // 更新
    Boolean update(@Param("id") Integer id, @Param("name") String name, @Param("nameZh") String nameZh);

    // 通过id获取
    Role getById(@Param("id") Integer id);

    // 获取所有角色
    List<Role> getAll(@Param("name") String name, @Param("nameZh") String nameZh);

}
