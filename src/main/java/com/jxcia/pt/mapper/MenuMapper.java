package com.jxcia.pt.mapper;

import com.jxcia.pt.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper {

    // 获取所有的菜单
    List<Menu> getAll();

    // 通过id获取菜单
    Menu getById(@Param("id") Integer id);

    // 增加菜单
    Boolean insert(@Param("pattern") String pattern);

    // 修改菜单
    Boolean update(@Param("id") Integer id, @Param("pattern") String pattern);

    // 删除菜单
    Boolean delete(@Param("id") Integer id);

}
