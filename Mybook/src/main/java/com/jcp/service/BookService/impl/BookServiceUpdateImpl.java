package com.jcp.service.BookService.impl;

import com.jcp.domain.Book;
import com.jcp.service.BookService.BookServiceUpdate;
import com.jcp.service.DaoService.BookService;
import com.jcp.service.DaoService.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookServiceUpdateImpl implements BookServiceUpdate {
    @Override
    public void Update(int t, JFrame Fupdate, Book book) {
        JTextArea textName= null;
        JTextArea textAuthor = null;
        JTextArea textPress = null;
        JTextArea textCount = null;
        JPanel p1 = null;
        JPanel p2 = null;
        JPanel p3 = null;
        JPanel p4 = null;

        if (t == 1) {
            //书名
            p1 = new JPanel();
            JLabel labelName = new JLabel("修改后的书名：");
            textName = new JTextArea(1, 10);
            p1.add(labelName);
            p1.add(textName);
            Fupdate.add(p1);
            Fupdate.setVisible(true);
        }
        else if (t == 2) {
            //作者
            p2 = new JPanel();
            JLabel labelAuthor = new JLabel("修改后的作者：");
            textAuthor = new JTextArea(1, 10);
            p2.add(labelAuthor);
            p2.add(textAuthor);
            Fupdate.add(p2);
            Fupdate.setVisible(true);
        } else if (t == 3) {
            //出版社
            p3 = new JPanel();
            JLabel labelPress = new JLabel("修改后的出版社：");
            textPress = new JTextArea(1, 10);
            p3.add(labelPress);
            p3.add(textPress);
            Fupdate.add(p3);
            Fupdate.setVisible(true);
        } else if (t == 4) {
            //藏书量
            p4 = new JPanel();
            JLabel labelCount = new JLabel("修改后的藏书量：");
            textCount = new JTextArea(1, 10);
            p4.add(labelCount);
            p4.add(textCount);
            Fupdate.add(p4);
            Fupdate.setVisible(true);
        }
        JPanel panelButtonChanged = new JPanel();
        JButton buttonChanged = new JButton("修改");
        panelButtonChanged.add(buttonChanged);
        Fupdate.add(panelButtonChanged);
        //final这些组件才可在内部类使用
        JTextArea finalTextName = textName;
        JTextArea finalTextAuthor = textAuthor;
        JTextArea finalTextPress = textPress;
        JTextArea finalTextCount = textCount;
        JPanel finalP1 = p1;
        JPanel finalP2 = p2;
        JPanel finalP3 = p3;
        JPanel finalP4 = p4;

        buttonChanged.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent eee) {
                if (eee.getSource() == buttonChanged) {
                    if (t == 1) {
                        book.setName(finalTextName.getText());
                        Fupdate.remove(finalP1);
                        Fupdate.remove(panelButtonChanged);
                        Fupdate.setVisible(true);
                    } else if (t == 2) {
                        book.setAuthor(finalTextAuthor.getText());
                        Fupdate.remove(finalP2);
                        Fupdate.remove(panelButtonChanged);
                        Fupdate.setVisible(true);
                    } else if (t == 3) {
                        book.setPress(finalTextPress.getText());
                        Fupdate.remove(finalP3);
                        Fupdate.remove(panelButtonChanged);
                        Fupdate.setVisible(true);
                    } else if (t == 4) {
                        book.setCount(Integer.parseInt(finalTextCount.getText()));
                        Fupdate.remove(finalP4);
                        Fupdate.remove(panelButtonChanged);
                        Fupdate.setVisible(true);
                    }
                    //持久层
                    String config="applicationContext.xml";
                    ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
                    BookService bookService=(BookService) ctx.getBean("bookService");
                    bookService.updateBook(book);
                }
            }
        });
    }
}
