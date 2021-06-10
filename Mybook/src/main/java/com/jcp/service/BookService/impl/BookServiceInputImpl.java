package com.jcp.service.BookService.impl;

import com.jcp.domain.Book;
import com.jcp.service.BookService.BookServiceInput;
import com.jcp.service.DaoService.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.util.List;

public class BookServiceInputImpl implements BookServiceInput {
    @Override
    public void input(Book book) {
        String config="applicationContext.xml";
        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
        BookService bookService=(BookService) ctx.getBean("bookService");
        List<Book> books=bookService.queryBook();

        boolean isSRepeated=false;
        for(int i=0;i<books.size();i++){
            if(books.get(i).getNo()==book.getNo()){
                isSRepeated=true;
                break;
            }
        }
        if(!isSRepeated) bookService.addBook(book);
        else JOptionPane.showMessageDialog(null, "书号重复，重新输入！");

    }
}
