package com.jxcia.pt.mapper;

import com.jxcia.pt.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    // 根据username获取User
    User loadUserByUsername(String username);

}
