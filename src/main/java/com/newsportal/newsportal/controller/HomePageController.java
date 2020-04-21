package com.newsportal.newsportal.controller;

import com.newsportal.newsportal.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("")
    public ModelAndView modelAndView(ModelAndView modelAndView){
        modelAndView.addObject("homeActive", "active");
        modelAndView.addObject("posts", postRepository.findLast5PostsOrderByDescCreated_At());
        modelAndView.setViewName("home.jsp");
        return modelAndView;
    }
}
