package com.thoughtworks.springbootemployee.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultEnum {
    DATA_NOT_FOUND("data not found"),
    ILLEGALE_OPERATION("illegale operation");
    private final String msg;

    public String getMsg() {
        return this.msg;
    }
}
