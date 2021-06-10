package com.jcp.dao;

import com.jcp.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    int insertUser(User user);
    List<User> selectUser();
    void deleteUser(@Param("id") Integer ID);
    User selectOneUser(@Param("id") Integer ID);
    void updateUser(User user);
}
