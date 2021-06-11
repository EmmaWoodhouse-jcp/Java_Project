package com.jcp.excep;

public class UserNotExist extends RuntimeException{
    public UserNotExist() { super();}
    public UserNotExist(String message) {
        super(message);
    }
}
