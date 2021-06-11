package com.jcp.service.UserService.impl;

import com.jcp.domain.User;
import com.jcp.service.DaoService.UserService;
import com.jcp.service.UserService.UserServiceDelete;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.util.List;

public class UserServiceDeleteImpl implements UserServiceDelete {
    @Override
    public void Delete(Integer ID) {
        String config="applicationContext.xml";
        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
        //通过bean标签获取对象
        UserService userService=(UserService) ctx.getBean("userService");
        List<User> users=userService.queryUsers();

        boolean isSeek=false;
        for(int i=0;i<users.size();i++){
            if(users.get(i).getID()==ID){
                isSeek=true;
                break;
            }
        }
        if (isSeek) {
            userService.removeUser(ID);
            JOptionPane.showMessageDialog(null, "删除成功！");
        }
        else JOptionPane.showMessageDialog(null, "不存在该用户！");
    }
}
