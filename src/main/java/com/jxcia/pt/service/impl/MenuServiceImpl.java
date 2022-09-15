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
    public List<Menu> getAll() {
        return menuMapper.getAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Menu getById(Integer id) {
        return menuMapper.getById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean insert(String pattern) {
        return menuMapper.insert(pattern);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean update(Integer id, String pattern) {
        return menuMapper.update(id, pattern);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean delete(Integer id) {
        return menuMapper.delete(id);
    }

}
