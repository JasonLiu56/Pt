package com.jxcia.pt.service;

import com.jxcia.pt.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuService {

    // 查询所有菜单
    List<Menu> getAll();

    // 通过id查询菜单
    Menu getById(Integer id);

    // 增加菜单
    Boolean insert(String pattern);

    // 修改菜单
    Boolean update(Integer id, String pattern);

    // 删除菜单
    Boolean delete(Integer id);

}
