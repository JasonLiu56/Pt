package com.jxcia.pt.service.impl;

import com.jxcia.pt.entity.Role;
import com.jxcia.pt.mapper.RoleMapper;
import com.jxcia.pt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Boolean insert(String name, String nameZh) {
        return roleMapper.insert(name, nameZh);
    }

    @Override
    public Boolean delete(Integer id) {
        return roleMapper.delete(id);
    }

    @Override
    public Boolean update(Integer id, String name, String nameZh) {
        return roleMapper.update(id, name, nameZh);
    }

    @Override
    public Role getById(Integer id) {
        return roleMapper.getById(id);
    }

    @Override
    public List<Role> getAll(String name, String nameZh) {
        return roleMapper.getAll(name, nameZh);
    }

}
