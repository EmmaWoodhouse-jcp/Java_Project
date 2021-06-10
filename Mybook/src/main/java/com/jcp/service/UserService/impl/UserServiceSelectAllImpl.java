package com.jcp.service.UserService.impl;

import com.jcp.domain.User;
import com.jcp.service.DaoService.UserService;
import com.jcp.service.UserService.UserServiceSelectAll;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class UserServiceSelectAllImpl implements UserServiceSelectAll {
    @Override
    public String SelectAll() {
        String config="applicationContext.xml";
        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
        UserService userService=(UserService) ctx.getBean("userService");
        List<User> users=userService.queryUsers();

        StringBuffer textAll=new StringBuffer();
        for(int i=0;i<users.size();i++){
            textAll.append("用户<<"+i+">>");
            textAll.append("账号: "+users.get(i).getID());
            textAll.append("\n");
            textAll.append("密码: "+ users.get(i).getPassword());
            textAll.append("\n");
            int t=users.get(i).getType();
            if(t==1) textAll.append("用户类型: 普通读者");
            else if(t==2) textAll.append("用户类型: 图书管理员");
            else if(t==3) textAll.append("用户类型: 系统管理员");
            textAll.append("\n");
            textAll.append("名字: "+ users.get(i).getName());
            textAll.append("\n");
            textAll.append("单位: "+ users.get(i).getUnit());
            textAll.append("\n");
            textAll.append("电话号: "+users.get(i).getTelephone());
            textAll.append("\n");
            textAll.append("可借书数量: "+users.get(i).getCan_borrow());
            textAll.append("\n");
            textAll.append("\n");
        }
        String str=new String(textAll);
        return str;
    }
}
