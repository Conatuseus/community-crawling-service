package com.conatuseus.communityservice.domain.posts.service.dto;


import com.conatuseus.communityservice.domain.community.domain.Community;
import com.conatuseus.communityservice.domain.posts.domain.Posts;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class PostsResponse {

    private final Long id;
    private final String title;
    private final String link;
    private final Community community;
    private final String keyword;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;

    public PostsResponse(Posts posts) {
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.link = posts.getLink();
        this.community = posts.getCommunity();
        this.keyword = posts.getKeyword();
        this.createdDate = posts.getCreatedDate();
        this.modifiedDate = posts.getModifiedDate();
    }
}
