package com.conatuseus.communityservice.domain.posts.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {

    private String title;
    private String link;

    public PostsUpdateRequestDto(final String title, final String link) {
        this.title = title;
        this.link = link;
    }
}
