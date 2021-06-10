package com.jcp.service.DaoService;

import com.jcp.domain.Book;
import com.jcp.domain.User;

import java.util.List;

public interface BookService {
    int addBook(Book book);
    void removeBook(String no);
    void updateBook(Book book);
    List<Book> queryBook();
}
