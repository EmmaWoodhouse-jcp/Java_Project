package com.jcp.service.BookService.impl;

import com.jcp.domain.Book;
import com.jcp.service.BookService.BookServiceDelete;
import com.jcp.service.DaoService.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.util.List;

public class BookServiceDeleteImpl implements BookServiceDelete {
    @Override
    public void Delete(String no) {
        String config="applicationContext.xml";
        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
        //通过bean标签获取对象
        BookService bookService=(BookService) ctx.getBean("bookService");
        List<Book> books=bookService.queryBook();

        boolean isSeek=false;
        for(int i=0;i<books.size();i++){
            if(books.get(i).getNo().equals(no)){
                isSeek=true;
                break;
            }
        }
        if (isSeek) {
            bookService.removeBook(no);
            JOptionPane.showMessageDialog(null, "删除成功！");
        }
        else JOptionPane.showMessageDialog(null, "不存在该书！");
    }
}
