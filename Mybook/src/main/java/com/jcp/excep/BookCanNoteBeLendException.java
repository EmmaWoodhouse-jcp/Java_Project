package com.jcp.excep;

public class BookCanNoteBeLendException extends RuntimeException{
    public BookCanNoteBeLendException() { super();}
    public BookCanNoteBeLendException(String message) {
        super(message);
    }
}
