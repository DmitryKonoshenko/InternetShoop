package com.divanxan.internetshop.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handlerNoHandlerFoundException(){

        ModelAndView mv = new ModelAndView("error");

        mv.addObject("errorTitle", "Ошибка доступа");
        mv.addObject("errorDescription", "Эта страница не доступна");
        mv.addObject("title", "404 Error Page");

        return  mv;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ModelAndView handlerProductNotFoundException(){

        ModelAndView mv = new ModelAndView("error");

        mv.addObject("errorTitle", "Данный товар недоступен!");
        mv.addObject("errorDescription", "Товар что вы ищите недоступен!");
        mv.addObject("title", "Product Unavailable!");

        return  mv;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handlerException(Exception e){

        ModelAndView mv = new ModelAndView("error");

        mv.addObject("errorTitle", "Что-то пошло не так. Свяжитесь с администратором!");

        //for debag
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        e.printStackTrace(pw);
        // end debug

        mv.addObject("errorDescription", sw.toString());
        mv.addObject("title", "Ошибка");

        return  mv;
    }

    @ExceptionHandler(UserAccessException.class)
    public ModelAndView handlerUserAccessException(){

        ModelAndView mv = new ModelAndView("error");

        mv.addObject("errorTitle", "Данная страница не оступна!");
        mv.addObject("errorDescription", "Данная страница не оступна!");
        mv.addObject("title", "Access denied");

        return  mv;
    }
}
