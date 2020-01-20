package com.conatuseus.communityservice.domain.posts.web;

import com.conatuseus.communityservice.domain.posts.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/posts")
@Controller
public class PostsController {

    private final PostsService postsService;

    @GetMapping
    public String getPosts(@RequestParam final String community, @RequestParam final String keyword, final Model model) {
        model.addAttribute("posts",postsService.findByCommunityNameAndKeyword(community, keyword));
        return "posts";
    }
}
