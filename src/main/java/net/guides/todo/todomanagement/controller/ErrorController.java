package net.guides.todo.todomanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller("error")
public class ErrorController {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletRequest httpServletRequest, Exception e){
        ModelAndView mv = new ModelAndView();

        mv.addObject("exception", e.getLocalizedMessage());
        mv.addObject("url", httpServletRequest.getRequestURL());

        mv.setViewName("error");

        return mv;
    }
}
