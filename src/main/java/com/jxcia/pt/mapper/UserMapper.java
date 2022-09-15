package com.jxcia.pt.mapper;

import com.jxcia.pt.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    // 新增
    Boolean insert(@Param("username") String username, @Param("password") String password, @Param("nickname") String nickname);

    // 删除
    Boolean delete(@Param("id") Integer id);

    // 更新昵称
    Boolean updateNickname(@Param("id") Integer id, @Param("nickname") String nickname);

    // 更新密码
    Boolean updatePassword(@Param("id") Integer id, @Param("password") String password);

    // 通过id查找
    User findById(@Param("id") Integer id);

    // 通过条件查询分页查询
    List<User> findByPage(@Param("nickname") String nickname);

    // 根据username获取User
    User loadUserByUsername(String username);

}
