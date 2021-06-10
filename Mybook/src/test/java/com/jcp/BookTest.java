package com.jcp;

import com.jcp.domain.Book;
import com.jcp.domain.User;
import com.jcp.service.DaoService.BookService;
import com.jcp.service.DaoService.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class BookTest {
    @Test//添加书
    public void test(){
        String config="applicationContext.xml";
        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
        //获取spring容器中的Service对象
        BookService bookService=(BookService) ctx.getBean("bookService");
        int nums=bookService.addBook(new Book("1002","情感与理智","简.奥斯汀","译林出版社",100));
        System.out.println(nums);
    }
    @Test//删除书
    public void test2(){
        String config="applicationContext.xml";
        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
        //获取spring容器中的Service对象
        BookService bookService=(BookService) ctx.getBean("bookService");
        bookService.removeBook("1002");
    }
    @Test
    public void test3(){
        String config="applicationContext.xml";
        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);

        BookService bookService=(BookService) ctx.getBean("bookService");
        Book book=new Book("1002","情感与理智","简.奥斯汀","中国文艺出版社",100);
        bookService.updateBook(book);
        System.out.println(book);
    }
    @Test
    public void test4(){
        String config="applicationContext.xml";
        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);

        BookService bookService=(BookService) ctx.getBean("bookService");
        List<Book> books=bookService.queryBook();
        System.out.println(books);
    }
    @Test//Service添加书
    public void test5(){
        String config="applicationContext.xml";
        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
        BookService bookService=(BookService) ctx.getBean("bookService");
        List<Book> books=bookService.queryBook();
        System.out.println(books);

        bookService.addBook(new Book("1003","艾玛","简.奥斯汀","上海译文出版社",100));
    }
}
