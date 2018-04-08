package com.divanxan.internetshop.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Controller for management exceptions
 *
 * @version 1.0
 * @autor Dmitry Konoshenko
 * @since version1.0
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    /**
     * Controller for going beyond the site exception
     *
     * @return ModelAndView
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handlerNoHandlerFoundException() {
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("errorTitle", "Ошибка доступа");
        mv.addObject("errorDescription", "Эта страница не доступна");
        mv.addObject("title", "404 Error Page");
        logger.error(mv.toString());
        return mv;
    }

    /**
     * Controller for exception with not exist products
     *
     * @return ModelAndView
     */
    @ExceptionHandler(ProductNotFoundException.class)
    public ModelAndView handlerProductNotFoundException() {
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("errorTitle", "Данный товар недоступен!");
        mv.addObject("errorDescription", "Товар что вы ищите недоступен!");
        mv.addObject("title", "Product Unavailable!");
        logger.error(mv.toString());
        return mv;
    }

    /**
     * Controller for exception with not exist order detail
     *
     * @return ModelAndView
     */
    @ExceptionHandler(OrderNotFoundException.class)
    public ModelAndView handlerOrderNotFoundException() {
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("errorTitle", "Данный заказ недоступен!");
        mv.addObject("errorDescription", "Заказ что вы ищите недоступен!");
        mv.addObject("title", "Order Detail Unavailable!");
        logger.error(mv.toString());
        return mv;
    }

    /**
     * Controller for unexpected exception in application
     *
     * @param e - unexpected Exception in application
     * @return ModelAndView
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handlerException(Exception e) {
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("errorTitle", "Что-то пошло не так. Свяжитесь с администратором!");
        //for debag
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        // end debug
        mv.addObject("errorDescription", sw.toString());
        mv.addObject("title", "Ошибка");
        logger.error(mv.toString());
        return mv;
    }

    /**
     * Controller for user access exception
     *
     * @return ModelAndView
     */
    @ExceptionHandler(UserAccessException.class)
    public ModelAndView handlerUserAccessException() {
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("errorTitle", "Данная страница не доступна!");
        mv.addObject("errorDescription", "Данная страница не доступна!");
        mv.addObject("title", "Access denied");
        logger.error(mv.toString());
        return mv;
    }
}
