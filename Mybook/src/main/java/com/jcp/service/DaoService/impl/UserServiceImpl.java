package com.jcp.service.DaoService.impl;

import com.jcp.dao.UserDao;
import com.jcp.domain.User;
import com.jcp.service.DaoService.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    //引用类型dao
    private UserDao userDao;

    //使用set注入来赋值
    public void setUserDao(UserDao userDao){
        this.userDao=userDao;
    }

    @Override
    public int addUser(User user) {
        int num=userDao.insertUser(user);
        return num;
    }

    @Override
    public List<User> queryUsers() {
        List<User> list=userDao.selectUser();
        return list;
    }

    @Override
    public User queryOneUser(Integer ID) {
        User user=userDao.selectOneUser(ID);
        return user;
    }

    @Override
    public void removeUser(Integer ID) {
        userDao.deleteUser(ID);
    }

    @Override
    public void updateUser(User user){
        userDao.updateUser(user);
    }

}
