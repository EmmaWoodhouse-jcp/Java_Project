package com.jcp.excep;

public class AlreadyReturnedBook extends RuntimeException{
    public AlreadyReturnedBook() { super();}
    public AlreadyReturnedBook(String message) {
        super(message);
    }
}
