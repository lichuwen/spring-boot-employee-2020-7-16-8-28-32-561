package com.thoughtworks.springbootemployee.exception;

public class GloableException extends Exception {
    String msg;

    public GloableException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
