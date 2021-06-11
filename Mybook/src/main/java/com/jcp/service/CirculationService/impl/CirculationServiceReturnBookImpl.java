package com.jcp.service.CirculationService.impl;

import com.jcp.domain.Book;
import com.jcp.domain.Circulation;
import com.jcp.domain.User;
import com.jcp.excep.AlreadyReturnedBook;
import com.jcp.service.CirculationService.CirculationServiceReturnBook;
import com.jcp.service.DaoService.BookService;
import com.jcp.service.DaoService.CirculationService;
import com.jcp.service.DaoService.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.List;

public class CirculationServiceReturnBookImpl implements CirculationServiceReturnBook {
    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            readOnly = false,
            rollbackFor = {
                    NullPointerException.class,
                    AlreadyReturnedBook.class
            }
    )
    @Override
    public void Return(String name, String no, int year, int month, int day, int ID){
        String config="applicationContext.xml";
        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
        BookService bookService=(BookService) ctx.getBean("bookService");
        UserService userService=(UserService) ctx.getBean("userService");
        CirculationService circulationService=(CirculationService) ctx.getBean("circulationService");
        List<Circulation> circulations= circulationService.queryCirculation();

        Book book=bookService.queryOneBook(no);
        User user=userService.queryOneUser(Integer.parseInt(name));
        if(user==null){
            JOptionPane.showMessageDialog(null,"不存在此用户");
            throw new NullPointerException("账户是"+name+"的用户不存在");
        }
        if(book==null){
            JOptionPane.showMessageDialog(null,"不存在此书");
            throw new NullPointerException("书号是"+no+"的图书不存在");
        }

        boolean iscanReturn=false;
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
        if(!iscanReturn) {
            JOptionPane.showMessageDialog(null, "用户不欠这本书");
            throw new AlreadyReturnedBook("用户不欠这本书");
        }
        //用户和图书修改
        user.setCan_borrow(user.getCan_borrow() + 1);
        book.setCount(book.getCount() + 1);
        userService.updateUser(user);
        bookService.updateBook(book);
        //添加流水记录
        circulationService.addCirculation(new Circulation(name,Integer.parseInt(no),year,month,day,2,ID));
    }
}
