package com.jcp.service.UserService.impl;

import com.jcp.domain.User;
import com.jcp.service.DaoService.UserService;
import com.jcp.service.UserService.UserServiceSelect;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceSelectImpl implements UserServiceSelect {
    @Override
    public String Select(Integer ID) {
        String config="applicationContext.xml";
        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);

        UserService userService=(UserService) ctx.getBean("userService");
        User user=userService.queryOneUser(ID);

        if(user!=null){
            StringBuffer textShowMessage=new StringBuffer();
            textShowMessage.append("账号: "+user.getID());
            textShowMessage.append("\n");
            textShowMessage.append("密码: "+ user.getPassword());
            textShowMessage.append("\n");
            int t=user.getType();
            if(t==1) textShowMessage.append("用户类型: 普通读者");
            else if(t==2) textShowMessage.append("用户类型: 图书管理员");
            else if(t==3) textShowMessage.append("用户类型: 系统管理员");
            textShowMessage.append("\n");
            textShowMessage.append("名字: "+ user.getName());
            textShowMessage.append("\n");
            textShowMessage.append("单位: "+ user.getUnit());
            textShowMessage.append("\n");
            textShowMessage.append("电话号: "+user.getTelephone());
            textShowMessage.append("\n");
            textShowMessage.append("可借书数量: "+user.getCan_borrow());

            String str=new String(textShowMessage);
            return str;
        }
       else return "";
    }
}
