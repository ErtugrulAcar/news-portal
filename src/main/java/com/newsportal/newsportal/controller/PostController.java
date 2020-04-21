package com.newsportal.newsportal.controller;

import com.newsportal.newsportal.model.Post;
import com.newsportal.newsportal.model.PostGroup;
import com.newsportal.newsportal.model.User;
import com.newsportal.newsportal.repository.PermissionRepository;
import com.newsportal.newsportal.repository.PostRepository;
import com.newsportal.newsportal.repository.UserRepository;
import com.newsportal.newsportal.source.Perm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("haber")
public class PostController {
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @GetMapping("yeni")
    public ModelAndView newPostPage(ModelAndView modelAndView, HttpSession session){
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");

        if(isLoggedIn != null && isLoggedIn){
            int userId =(int) session.getAttribute("id");
            int groupId = (int) session.getAttribute("groupId");
            if(permissionRepository.hasPermission(Perm.CREATE_POST, groupId)){
                modelAndView.addObject("newPostActive", "active");
                modelAndView.addObject("groups", userRepository.findPostGroupsByUserId(userId));
                modelAndView.setViewName("newPost.jsp");
            }else{
                modelAndView.setViewName("redirect:/");
            }
        }else{
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;

    }

    @PostMapping("create")
    public ModelAndView createPost(ModelAndView modelAndView, HttpSession session, HttpServletRequest request){
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");

        if(isLoggedIn != null && isLoggedIn) {
            int userId = (int) session.getAttribute("id");
            int groupId = (int) session.getAttribute("groupId");
            if (permissionRepository.hasPermission(Perm.CREATE_POST, groupId)) {
                String title = request.getParameter("title");
                String postGroup = request.getParameter("post-group");
                String content = request.getParameter("content");
                String imgUrl = request.getParameter("img-url");

                postRepository.saveAndFlush(
                        new Post().setAuthor(new User().setId(userId))
                        .setVerified(false)
                        .setTitle(title)
                        .setPostGroup(new PostGroup().setId(Integer.parseInt(postGroup)))
                        .setContent(content)
                        .setImageUrl(imgUrl)
                );
                modelAndView.setViewName("redirect:/guncelle");
                return modelAndView;
            }
        }
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }


    @GetMapping("guncelle")
    public ModelAndView modelAndView(ModelAndView modelAndView, HttpSession session){
        System.out.println("gucenlleme sayfasında");
        return null;
    }
}
