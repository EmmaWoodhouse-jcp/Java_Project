package com.jcp.service.CirculationService.impl;

import com.jcp.domain.Book;
import com.jcp.domain.Circulation;
import com.jcp.domain.User;
import com.jcp.excep.BookCanNoteBeLendException;
import com.jcp.excep.UserCanNoteLendException;
import com.jcp.service.CirculationService.CirculationServiceLendBook;
import com.jcp.service.DaoService.BookService;
import com.jcp.service.DaoService.CirculationService;
import com.jcp.service.DaoService.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;

public class CirculationServiceLendBookImpl implements CirculationServiceLendBook {
    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            readOnly = false,
            rollbackFor = {
                    NullPointerException.class,
                    UserCanNoteLendException.class,
                    BookCanNoteBeLendException.class
            }
    )
    @Override
    public void Lend(String name, String no, int year, int month, int day,int ID) {
        String config="applicationContext.xml";
        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
        BookService bookService=(BookService) ctx.getBean("bookService");
        UserService userService=(UserService) ctx.getBean("userService");
        CirculationService circulationService=(CirculationService) ctx.getBean("circulationService");

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
        if(user.getCan_borrow()<1) {
            JOptionPane.showMessageDialog(null,"此用户已达到借书上限,请先还书");
            throw new UserCanNoteLendException("用户"+name+"剩余可借书数不足");

        }
        if(book.getCount()<1) {
            JOptionPane.showMessageDialog(null,"此书已无剩余，请等待其他用户还书");
            throw new BookCanNoteBeLendException("图书"+book.getName()+"剩余数量不足，请先等待其他用户还此书");
        }
                //用户和图书修改
        user.setCan_borrow(user.getCan_borrow()-1);
        book.setCount(book.getCount()-1);
        userService.updateUser(user);
        bookService.updateBook(book);
        //添加流水记录
        circulationService.addCirculation(new Circulation(name,Integer.parseInt(no),year,month,day,1,ID));
        JOptionPane.showMessageDialog(null,"借书成功");
    }
}
