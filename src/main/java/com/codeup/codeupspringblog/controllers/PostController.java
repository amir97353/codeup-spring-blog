package com.codeup.codeupspringblog.controllers;


import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postsDao;

    public PostController(PostRepository postsDao) {
        this.postsDao = postsDao;
    }

    //Here we have a new instance of the PostRepository


    @GetMapping("/posts")
    public String viewPosts(Model model) {
        model.addAttribute("posts", postsDao.findAll());
        return "posts/index";
    }


    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable long id, Model model) {
        Post post1 = postsDao.findById(id).get();
        model.addAttribute("post", post1);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String showPostForm() {
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String submitNewPost(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body, Model model) {
        Post post = new Post(title, body);
        postsDao.save(post);
        return "redirect:/posts";
    }
}