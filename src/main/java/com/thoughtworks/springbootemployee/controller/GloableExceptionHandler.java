package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.exception.GloableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GloableExceptionHandler {

    @ExceptionHandler(GloableException.class)
    public String gloableExceptionHandler(HttpServletRequest httpServletRequest, GloableException gloableException) {
        return gloableException.getMsg();
    }
}
