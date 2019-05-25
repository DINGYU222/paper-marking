package com.papermaking.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = PaperCreateErrorException.class) //@ExceptionHandler 该注解声明异常处理方法
    public String defaultErrorHandler(Model model, Exception e) throws Exception {

        return "redirect:/paper?message=" + e.getMessage();
    }
}
