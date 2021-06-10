package com.jcp.service.UserService.impl;

import com.jcp.domain.User;
import com.jcp.service.DaoService.UserService;
import com.jcp.service.UserService.UserServiceChangePassword;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserServiceChangePasswordImpl implements UserServiceChangePassword {
    @Override
    public void UpdatePassword(JFrame FchangePassword, User user) {
        //新密码
        JPanel p4=new JPanel();
        JLabel labelPassword_2=new JLabel("新密码：");
        JTextArea textPassword_2=new JTextArea(1,10);
        p4.add(labelPassword_2);p4.add(textPassword_2);
        //确定修改
        JPanel p5=new JPanel();
        JButton buttonConfirmChange=new JButton("修改密码");
        p5.add(buttonConfirmChange);
        //更新面板
        FchangePassword.add(p4);
        FchangePassword.add(p5);
        FchangePassword.repaint();
        FchangePassword.setVisible(true);

        buttonConfirmChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ee) {
                if(ee.getSource()==buttonConfirmChange){
                    String config="applicationContext.xml";
                    ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
                    UserService userService=(UserService) ctx.getBean("userService");
                    user.setPassword(textPassword_2.getText());
                    userService.updateUser(user);
                    JOptionPane.showMessageDialog(null, "修改成功");
                }
            }
        });
    }
}
