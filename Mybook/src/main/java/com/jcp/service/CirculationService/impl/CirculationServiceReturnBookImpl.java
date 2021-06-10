package com.jcp.service.CirculationService.impl;

import com.jcp.domain.Book;
import com.jcp.domain.Circulation;
import com.jcp.domain.User;
import com.jcp.service.CirculationService.CirculationServiceReturnBook;
import com.jcp.service.DaoService.BookService;
import com.jcp.service.DaoService.CirculationService;
import com.jcp.service.DaoService.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.util.List;

public class CirculationServiceReturnBookImpl implements CirculationServiceReturnBook {
    @Override
    public void Return(String name, String no, int year, int month, int day, int ID){
        String config="applicationContext.xml";
        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
        BookService bookService=(BookService) ctx.getBean("bookService");
        UserService userService=(UserService) ctx.getBean("userService");
        CirculationService circulationService=(CirculationService) ctx.getBean("circulationService");
        List<User> users=userService.queryUsers();
        List<Book> books=bookService.queryBook();
        List<Circulation> circulations= circulationService.queryCirculation();

        boolean isUserE = false;
        boolean isBookE = false;

        boolean iscanReturn=false;
        int markUser = -1;
        int markBook = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getID() == Integer.parseInt(name)) {
                isUserE = true;
                markUser = i;
                break;
            }
        }
        for (int i = 0; i < books.size(); i++) {
            if (no.equals(books.get(i).getNo())) {
                isBookE = true;
                markBook = i;
                break;
            }
        }
        for (int i = 0; i < circulations.size(); i++) {
            int a=Integer.parseInt(no);
            int b=Integer.parseInt(String.valueOf(circulations.get(i).getNo()));
            if (name.equals(String.valueOf(circulations.get(i).getName()))&&a==b) {//要还书号与书号 还书人与借阅人均相同
                if (circulations.get(i).getType() == 1){
                    iscanReturn = true;
                    circulations.get(i).setType(2);
                }
            }
        }
        if(iscanReturn){
            if (isUserE && isBookE) {
                //用户和图书修改
                users.get(markUser).setCan_borrow(users.get(markUser).getCan_borrow() + 1);
                books.get(markBook).setCount(books.get(markBook).getCount() + 1);
                userService.updateUser(users.get(markUser));
                bookService.updateBook(books.get(markBook));
                //添加流水记录
                circulationService.addCirculation(new Circulation(name,Integer.parseInt(no),year,month,day,2,ID));
            }
            else {
                if (!isUserE) JOptionPane.showMessageDialog(null, "不存在此用户");
                if (!isBookE) JOptionPane.showMessageDialog(null, "不存在此书");
            }
        } else JOptionPane.showMessageDialog(null, "已经还过此书了");
    }
}
