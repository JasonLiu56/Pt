package com.jxcia.pt.service;

import com.jxcia.pt.entity.Menu;

import java.util.List;

public interface MenuService {

    // 查询所有菜单
    List<Menu> getAllMenu();

    // 通过id查询菜单
    Menu getMenuById(Integer id);

}
