package com.jxcia.pt.mapper;

import com.jxcia.pt.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    // 获取所有的菜单
    List<Menu> getAllMenu();

    // 通过id获取菜单
    Menu getMenuById(Integer id);

}
