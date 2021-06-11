package com.jcp.dao;

import com.jcp.domain.Book;

import java.util.List;

public interface BookDao {
    int insertBook(Book book);
    void deleteBook(String no);
    void updateBook(Book book);
    List<Book> selectBook();
    Book selectOneBook(String no);

}
