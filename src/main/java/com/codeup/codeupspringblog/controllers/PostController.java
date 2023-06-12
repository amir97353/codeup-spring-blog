package com.codeup.codeupspringblog.controllers;


import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import com.codeup.codeupspringblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postsDao;

    private final UserRepository userDao;

    private final EmailService emailService;

    public PostController(PostRepository postsDao, UserRepository userDao, EmailService emailService) {
        this.postsDao = postsDao;
        this.userDao = userDao;
        this.emailService = emailService;
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
    public String showPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String submitNewPost(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body, Model model) {
        Post post = new Post(title, body);
        User user = userDao.findById(1L).get();
        post.setUser(user);
        postsDao.save(post);
//

        emailService.prepareAndSend(post, "New Post Created!", post.getBody());
        postsDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(@PathVariable long id, Model model) {
        if(postsDao.findById(id).isPresent()) {
            Post postToEdit = postsDao.findById(id).get();
            model.addAttribute("post", postToEdit);
        }
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@ModelAttribute Post newPost) {
        User user = userDao.findById(1L).get();
        newPost.setUser(user);
        postsDao.save(newPost);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id) {
        if (postsDao.existsById(id)) {
            postsDao.deleteById(id);
        }
        return "redirect:/posts";
    }


}