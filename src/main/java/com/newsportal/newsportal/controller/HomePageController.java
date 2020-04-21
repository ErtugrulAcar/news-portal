package com.newsportal.newsportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {
    @GetMapping("")
    public ModelAndView modelAndView(ModelAndView modelAndView){
        modelAndView.addObject("homeActive", "<span class=\"sr-only\">(current)</span>");
        modelAndView.setViewName("home.jsp");
        return modelAndView;
    }
}
