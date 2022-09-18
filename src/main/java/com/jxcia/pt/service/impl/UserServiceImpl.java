package com.jxcia.pt.service.impl;

import com.github.pagehelper.PageHelper;
import com.jxcia.pt.entity.User;
import com.jxcia.pt.mapper.UserMapper;
import com.jxcia.pt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean insert(String username, String password, String nickname, List<Integer> roleIds) {
        // 增加用户
        User user = new User(username, password, nickname);
        Boolean flag = userMapper.insert(user);
        // 增加用户角色
        for (Integer roleId : roleIds) {
            flag = (userMapper.insertUserRole(user.getId(), roleId));
        }
        return flag;
    }

    @Override
    public Boolean isExist(String username) {
        return userMapper.isExistByUsername(username);
    }

    @Override
    public Boolean isExist(Integer id) {
        return userMapper.isExistById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean delete(Integer id) {
        // 删除用户
        Boolean flag = userMapper.delete(id);
        // 删除用户角色
        flag = (userMapper.deleteUserRole(id) && flag);
        return flag;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean updateNickname(Integer id, String nickname) {
        return userMapper.updateNickname(id, nickname);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean updatePassword(Integer id, String password) {
        return userMapper.updatePassword(id, password);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean updateRoles(Integer id, List<Integer> roleIds) {
        // 删除原来用户角色
        Boolean flag = userMapper.deleteUserRole(id);
        // 插入新的用户角色
        for (Integer roleId : roleIds) {
            flag = (userMapper.insertUserRole(id, roleId) && flag);
        }

        return flag;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User getById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> getByPage(Integer pageNum, Integer pageSize, String nickname) {
        PageHelper.startPage(pageNum - 1, pageSize);
        return userMapper.findByPage(nickname);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer countByPage(String nickname) {
        return userMapper.countByPage(nickname);
    }
}
