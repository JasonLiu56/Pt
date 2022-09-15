package com.jxcia.pt.service.impl;

import com.jxcia.pt.entity.Menu;
import com.jxcia.pt.mapper.MenuMapper;
import com.jxcia.pt.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getAll() {
        return menuMapper.getAll();
    }

    @Override
    public Menu getById(Integer id) {
        return menuMapper.getById(id);
    }

    @Override
    public Boolean insert(String pattern) {
        return menuMapper.insert(pattern);
    }

    @Override
    public Boolean update(Integer id, String pattern) {
        return menuMapper.update(id, pattern);
    }

    @Override
    public Boolean delete(Integer id) {
        return menuMapper.delete(id);
    }

}
