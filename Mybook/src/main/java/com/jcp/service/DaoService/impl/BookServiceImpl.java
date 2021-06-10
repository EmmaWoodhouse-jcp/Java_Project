package com.jcp.service.DaoService.impl;

import com.jcp.dao.BookDao;
import com.jcp.domain.Book;
import com.jcp.service.DaoService.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao;
    public void setBookDao(BookDao bookDao){
        this.bookDao=bookDao;
    }

    @Override
    public int addBook(Book book) {
        int num=bookDao.insertBook(book);
        return num;
    }

    @Override
    public void removeBook(String no) {
        bookDao.deleteBook(no);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public List<Book> queryBook() {
        List<Book> books=bookDao.selectBook();
        return books;
    }
}
