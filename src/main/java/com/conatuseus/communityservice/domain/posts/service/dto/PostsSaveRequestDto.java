package com.conatuseus.communityservice.domain.posts.service.dto;

import com.conatuseus.communityservice.domain.community.domain.Community;
import com.conatuseus.communityservice.domain.posts.domain.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String link;
    private String keyword;

    @Builder
    public PostsSaveRequestDto(final String title, final String link, final String keyword) {
        this.title = title;
        this.link = link;
        this.keyword = keyword;
    }

    public Posts toEntity(final Community community) {
        return Posts.builder()
            .title(title)
            .link(link)
            .community(community)
            .keyword(keyword)
            .build();
    }
}
