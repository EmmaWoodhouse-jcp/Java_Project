package com.jcp.excep;

public class UserCanNoteLendException extends RuntimeException{
    public UserCanNoteLendException() { super(); }
    public UserCanNoteLendException(String message) {
        super(message);
    }
}
