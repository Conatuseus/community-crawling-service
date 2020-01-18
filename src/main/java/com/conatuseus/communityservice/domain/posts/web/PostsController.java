package com.conatuseus.communityservice.domain.posts.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/posts")
@Controller
public class PostsController {

    @GetMapping
    public String getPosts(@RequestParam final String community, @RequestParam final String keyword) {
        return "posts";
    }
}
