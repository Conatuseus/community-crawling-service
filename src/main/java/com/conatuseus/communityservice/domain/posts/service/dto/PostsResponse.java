package com.conatuseus.communityservice.domain.posts.service.dto;


import com.conatuseus.communityservice.domain.posts.domain.Posts;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostsResponse {

    private final Long id;
    private final String title;
    private final String link;
    private final String community;
    private final String keyword;

    public PostsResponse(Posts posts) {
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.link = posts.getLink();
        this.community = posts.getCommunity();
        this.keyword = posts.getKeyword();
    }
}