package com.jcp.excep;

public class BookNotExits extends RuntimeException{
    public BookNotExits() { super();}
    public BookNotExits(String message) {
        super(message);
    }
}
