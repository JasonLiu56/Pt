package com.jxcia.pt.mapper;

import com.jxcia.pt.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    // 新增
    Boolean insert(User user);

    // 新增用户角色
    Boolean insertUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    // 根据username判断是否存在
    Boolean isExistByUsername(@Param("username") String username);

    // 根据id判断是否存在
    Boolean isExistById(@Param("id") Integer id);

    // 删除
    Boolean delete(@Param("id") Integer id);

    // 删除用户角色
    Boolean deleteUserRole(@Param("userId") Integer userId);

    // 更新昵称
    Boolean updateNickname(@Param("id") Integer id, @Param("nickname") String nickname);

    // 更新密码
    Boolean updatePassword(@Param("id") Integer id, @Param("password") String password);

    // 更换密码
    Boolean changePassword(@Param("username") String username, @Param("password") String password);

    // 通过id查找
    User findById(@Param("id") Integer id);

    // 通过username查找
    User findByUsername(@Param("username") String username);

    // 通过条件查询分页查询
    List<User> findByPage(@Param("nickname") String nickname);

    // 通过条件统计查询总数
    Integer countByPage(@Param("nickname") String nickname);

    // 根据username获取User
    User loadUserByUsername(String username);

}
