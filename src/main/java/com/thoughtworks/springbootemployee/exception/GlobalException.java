package com.thoughtworks.springbootemployee.exception;

public class GlobalException extends Exception {
    Integer code;
    String msg;

    public GlobalException(String msg) {
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
