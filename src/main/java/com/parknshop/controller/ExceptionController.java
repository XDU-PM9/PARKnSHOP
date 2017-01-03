package com.parknshop.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by Lenovo on 2017/1/3.
 */
@ControllerAdvice
public class ExceptionController {


    @ExceptionHandler(Exception.class)
    public String exceptionHandler(){
        return "/customer/404.jsp";
    }
}
