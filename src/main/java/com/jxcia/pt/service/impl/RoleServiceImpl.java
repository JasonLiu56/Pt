package com.jxcia.pt.service.impl;

import com.jxcia.pt.entity.Role;
import com.jxcia.pt.mapper.RoleMapper;
import com.jxcia.pt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean insert(String name, String nameZh) {
        return roleMapper.insert(name, nameZh);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(String name, String nameZh) {
        return roleMapper.isExistByNameOrNameZh(name, nameZh);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer id) {
        return roleMapper.isExistById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer id, String name, String nameZh) {
        return roleMapper.isExistByNotIdAndNameOrNameZh(id, name, nameZh);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean delete(Integer id) {
        return roleMapper.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean update(Integer id, String name, String nameZh) {
        return roleMapper.update(id, name, nameZh);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Role getById(Integer id) {
        return roleMapper.getById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Role> getAll(String name, String nameZh) {
        return roleMapper.getAll(name, nameZh);
    }

}
