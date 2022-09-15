package com.jxcia.pt.service.impl;

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
    public Boolean insert(String username, String password, String nickname) {
        return userMapper.insert(username, password, nickname);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean delete(Integer id) {
        return userMapper.delete(id);
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
    @Transactional(propagation = Propagation.SUPPORTS)
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> findByPage(String nickname) {
        return userMapper.findByPage(nickname);
    }

}
