package com.thoughtworks.springbootemployee.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultEnum {
    DATA_NOT_FOUND("数据不存在"),
    ILLEGALE_OPERATION("非法操作");
    private final String msg;

    public String getMsg() {
        return this.msg;
    }
}
