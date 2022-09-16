package com.jxcia.pt.mapper;

import com.jxcia.pt.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper {

    // 获取所有的菜单
    List<Menu> getAll(String pattern);

    // 通过id获取菜单
    Menu getById(@Param("id") Integer id);

    // 增加菜单
    Boolean insert(Menu menu);

    // 通过pattern判断是否存在
    Boolean isExistByPattern(@Param("pattern") String pattern);

    // 通过id判断是否存在
    Boolean isExistById(@Param("id") Integer id);

    // 通过pattern判断是否存在(同时不是一个id)
    Boolean isExistByNotIdAndPattern(@Param("id") Integer id, @Param("pattern") String pattern);

    // 新增菜单角色
    Boolean insertMenuRole(@Param("menuId") Integer menuId, @Param("roleId") Integer roleId);

    // 修改菜单
    Boolean update(@Param("id") Integer id, @Param("pattern") String pattern);

    // 删除菜单
    Boolean delete(@Param("id") Integer id);

    // 删除菜单角色
    Boolean deleteMenuRole(@Param("menuId") Integer menuId);

}
