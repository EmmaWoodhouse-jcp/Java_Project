package com.jcp.service.UserService.impl;

import com.jcp.domain.User;
import com.jcp.service.DaoService.UserService;
import com.jcp.service.UserService.UserServiceInput;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.util.List;

public class UserServiceInputImpl implements UserServiceInput {
    @Override
    public void input(User user) {
        String config="applicationContext.xml";
        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
        //通过bean标签获取对象
        UserService userService=(UserService) ctx.getBean("userService");
        List<User> users=userService.queryUsers();

        boolean isSRepeated=false;
        for(int i=0;i<users.size();i++){
            if(users.get(i).getID()==user.getID()) isSRepeated=true;
        }
        if(!isSRepeated) userService.addUser(user);
        else JOptionPane.showMessageDialog(null, "账户重复，重新输入！");
    }
}
