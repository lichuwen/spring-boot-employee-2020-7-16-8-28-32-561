package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.exception.GlobalException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GloableExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public String gloableExceptionHandler(HttpServletRequest httpServletRequest, GlobalException globalException) {
        return globalException.getMsg();
    }
}
