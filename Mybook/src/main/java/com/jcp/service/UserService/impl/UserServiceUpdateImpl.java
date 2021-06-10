package com.jcp.service.UserService.impl;

import com.jcp.domain.User;
import com.jcp.service.DaoService.UserService;
import com.jcp.service.UserService.UserServiceUpdate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserServiceUpdateImpl implements UserServiceUpdate {
    @Override
    public void Update(int t, JFrame Fupdate,User user) {
        JPanel p1 =null;
        JPanel p2 =null;
        JPanel p3 =null;
        JPanel p4 =null;
        JPanel p5 =null;
        JPanel p6 =null;

        JTextArea textPassword = null;
        JComboBox<String> cmbType =null;
        JTextArea textName = null;
        JTextArea textUnit = null;
        JTextArea textTelephone = null;
        JTextArea textCan_borrow = null;

        if (t == 1) {
            //密码
            p1 = new JPanel();
            JLabel labelPassword = new JLabel("修改后的密码（0~14位数字）：");
            textPassword = new JTextArea(1, 10);
            p1.add(labelPassword);
            p1.add(textPassword);
            Fupdate.add(p1);
            Fupdate.setVisible(true);
        } else if (t == 2) {
            //用户类型
            p2 = new JPanel();
            JLabel labelType = new JLabel("修改后的类型");
            cmbType = new JComboBox<String>();
            cmbType.addItem("--请选择--");
            cmbType.addItem("1.普通读者");
            cmbType.addItem("2.图书管理员");
            cmbType.addItem("3.系统管理员");
            p2.add(labelType);
            p2.add(cmbType);
            Fupdate.add(p2);
            Fupdate.setVisible(true);
        } else if (t == 3) {
            //姓名
            p3 = new JPanel();
            JLabel labelName = new JLabel("修改后的姓名：");
            textName = new JTextArea(1, 10);
            p3.add(labelName);
            p3.add(textName);
            Fupdate.add(p3);
            Fupdate.setVisible(true);
        } else if (t == 4) {
            //单位
            p4 = new JPanel();
            JLabel labelUnit = new JLabel("修改后的单位：");
            textUnit = new JTextArea(1, 10);
            p4.add(labelUnit);
            p4.add(textUnit);
            Fupdate.add(p4);
            Fupdate.setVisible(true);
        } else if (t == 5) {
            //电话
            p5 = new JPanel();
            JLabel labelTelephone = new JLabel("修改后的电话(11位数字（China）)：");
            textTelephone = new JTextArea(1, 10);
            textTelephone.setBounds(10, 10, 10, 10);
            p5.add(labelTelephone);
            p5.add(textTelephone);
            Fupdate.add(p5);
            Fupdate.setVisible(true);
        } else if (t == 6) {
            //可借书数
            p6 = new JPanel();
            JLabel labelCan_borrow = new JLabel("修改后的数量");
            textCan_borrow = new JTextArea(1,3);
            p6.add(labelCan_borrow);
            p6.add(textCan_borrow);
            Fupdate.add(p6);
            Fupdate.setVisible(true);
        }

        JPanel panelButtonChanged = new JPanel();
        JButton buttonChanged = new JButton("修改");
        panelButtonChanged.add(buttonChanged);
        Fupdate.add(panelButtonChanged);
        //final这些组件才可在内部类使用
        JTextArea finalTextPassword = textPassword;
        JComboBox<String> finalCmbType = cmbType;
        JTextArea finalTextName = textName;
        JTextArea finalTextUnit = textUnit;
        JTextArea finalTextTelephone = textTelephone;
        JTextArea finalTextCan_borrow = textCan_borrow;
        JPanel finalP1 = p1;
        JPanel finalP2 = p2;
        JPanel finalP3 = p3;
        JPanel finalP4 = p4;
        JPanel finalP5 = p5;
        JPanel finalP6 = p6;


        buttonChanged.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent eee) {
                if(eee.getSource()==buttonChanged) {
                    if (t == 1) {
                        user.setPassword(finalTextPassword.getText());
                        Fupdate.remove(finalP1);
                        Fupdate.remove(panelButtonChanged);
                        Fupdate.setVisible(true);
                    } else if (t == 2) {
                        user.setType(finalCmbType.getSelectedIndex());
                        Fupdate.remove(finalP2);
                        Fupdate.remove(panelButtonChanged);
                        Fupdate.setVisible(true);
                    } else if (t == 3) {
                        user.setName(finalTextName.getText());
                        Fupdate.remove(finalP3);
                        Fupdate.remove(panelButtonChanged);
                        Fupdate.setVisible(true);
                    } else if (t == 4) {
                        user.setUnit(finalTextUnit.getText());
                        Fupdate.remove(finalP4);
                        Fupdate.remove(panelButtonChanged);
                        Fupdate.setVisible(true);
                    } else if (t == 5) {
                        user.setTelephone(Long.parseLong(finalTextTelephone.getText()));
                        Fupdate.remove(finalP5);
                        Fupdate.remove(panelButtonChanged);
                        Fupdate.setVisible(true);
                    } else if (t == 6) {
                        user.setCan_borrow(Integer.parseInt(finalTextCan_borrow.getText()));
                        Fupdate.remove(finalP6);
                        Fupdate.remove(panelButtonChanged);
                        Fupdate.setVisible(true);
                    }

                    //持久层
                    String config="applicationContext.xml";
                    ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
                    UserService userService=(UserService) ctx.getBean("userService");
                    userService.updateUser(user);
                }
            }
        });
    }
}
