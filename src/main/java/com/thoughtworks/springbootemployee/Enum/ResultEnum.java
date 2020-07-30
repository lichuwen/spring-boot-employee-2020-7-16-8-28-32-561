package com.thoughtworks.springbootemployee.Enum;

public enum ResultEnum {
    DATA_NOT_FOUND("数据不存在"),
    ILLEGALE_OPERATION("非法操作");
    private final String msg;

    ResultEnum(String msg) {
        this.msg = msg;
    }
}
