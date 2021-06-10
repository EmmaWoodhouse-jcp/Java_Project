package com.jcp.service.CirculationService.impl;

import com.jcp.domain.Book;
import com.jcp.domain.Circulation;
import com.jcp.domain.User;
import com.jcp.service.CirculationService.CirculationServiceLendBook;
import com.jcp.service.DaoService.BookService;
import com.jcp.service.DaoService.CirculationService;
import com.jcp.service.DaoService.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.util.List;

public class CirculationServiceLendBookImpl implements CirculationServiceLendBook {
    @Override
    public void Lend(String name, String no, int year, int month, int day,int ID) {
        String config="applicationContext.xml";
        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
        BookService bookService=(BookService) ctx.getBean("bookService");
        UserService userService=(UserService) ctx.getBean("userService");
        CirculationService circulationService=(CirculationService) ctx.getBean("circulationService");
        List<User> users=userService.queryUsers();
        List<Book> books=bookService.queryBook();

        boolean isUserHave=false;
        boolean isUserE=false;
        boolean isBookHave=false;
        boolean isBookE=false;
        int markUser=-1;
        int markBook=-1;
        for(int i=0;i<users.size();i++){
            if(users.get(i).getID()==Integer.parseInt(name)){
                isUserE=true;
                System.out.println(users.get(i).getCan_borrow());
                if(users.get(i).getCan_borrow()>0){
                    markUser=i;
                    isUserHave=true;
                    break;
                }
            }
        }
        for(int i=0;i<books.size();i++){
            if(no.equals(books.get(i).getNo())){
                isBookE=true;
                if(books.get(i).getCount()>0){
                    markBook=i;
                    isBookHave=true;
                    break;
                }
            }
        }
        if(isUserE&&isBookE){
            if(isUserHave && isBookHave){
                //用户和图书修改
                users.get(markUser).setCan_borrow(users.get(markUser).getCan_borrow()-1);
                books.get(markBook).setCount(books.get(markBook).getCount()-1);
                userService.updateUser(users.get(markUser));
                bookService.updateBook(books.get(markBook));
                //添加流水记录
                circulationService.addCirculation(new Circulation(name,Integer.parseInt(no),year,month,day,1,ID));
            }
            else{
                if(!isUserHave) JOptionPane.showMessageDialog(null,"此用户已达到借书上限,请先还书");
                if(!isBookHave) JOptionPane.showMessageDialog(null,"此书已无剩余，请等待其他用户还书");
            }
        }
        else{
            if(!isUserE) JOptionPane.showMessageDialog(null,"不存在此用户");
            if(!isBookE) JOptionPane.showMessageDialog(null,"不存在此书");
        }
    }
}
