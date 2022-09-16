package com.jxcia.pt.service.impl;

import com.jxcia.pt.entity.Menu;
import com.jxcia.pt.mapper.MenuMapper;
import com.jxcia.pt.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Menu> getAll(String pattern) {
        return menuMapper.getAll(pattern);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Menu getById(Integer id) {
        return menuMapper.getById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean insert(String pattern, List<Integer> roleIds) {
        // 新增菜单
        Menu menu = new Menu(pattern);
        Boolean flag = menuMapper.insert(menu);

        // 新增菜单角色
        for (Integer roleId : roleIds) {
            flag = (menuMapper.insertMenuRole(menu.getId(), roleId) && flag);
        }

        return flag;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(String pattern) {
        return menuMapper.isExistByPattern(pattern);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer id) {
        return menuMapper.isExistById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer id, String pattern) {
        return menuMapper.isExistByNotIdAndPattern(id, pattern);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean update(Integer id, String pattern, List<Integer> roleIds) {
        // 更新菜单
        Boolean flag = menuMapper.update(id, pattern);
        // 删除原来菜单角色
        flag = menuMapper.deleteMenuRole(id) && flag;
        // 新增菜单角色
        for (Integer roleId : roleIds) {
            flag = (menuMapper.insertMenuRole(id, roleId) && flag);
        }
        return flag;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean delete(Integer id) {
        // 删除菜单
        Boolean flag = menuMapper.delete(id);

        // 删除菜单角色
        flag = menuMapper.deleteMenuRole(id) && flag;

        return flag;
    }

}
