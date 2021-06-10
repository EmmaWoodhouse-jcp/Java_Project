package com.jcp.domain;

import java.io.Serializable;

public class Book implements Serializable {
    private String no;                             //书号
    private String name;                        //书名
    private String author;                      //作者名
    private String press;                       //出版社
    private int count;                          //藏书量

    public Book(String no, String name, String author, String press, int count) {
        this.no = no;
        this.name = name;
        this.author = author;
        this.press = press;
        this.count = count;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Book{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", press='" + press + '\'' +
                ", count=" + count +
                '}';
    }
}
