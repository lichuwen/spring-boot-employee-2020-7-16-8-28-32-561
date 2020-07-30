package com.thoughtworks.springbootemployee.exception;

public class gloableException extends Exception {
    String msg;

    public gloableException(String message, String msg) {
        super(message);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
