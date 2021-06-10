package com.jcp.service.DaoService;

import com.jcp.dao.UserDao;
import com.jcp.domain.User;

import java.util.List;

public interface UserService {
    int addUser(User user);
    List<User> queryUsers();
    User queryOneUser(Integer ID);
    void removeUser(Integer ID);
    void updateUser(User user);

}
