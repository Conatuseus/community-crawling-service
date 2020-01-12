package com.conatuseus.communityservice.domain.posts.service.dto;

import com.conatuseus.communityservice.domain.posts.domain.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String link;
    private String community;
    private String keyword;

    @Builder
    public PostsSaveRequestDto(final String title, final String link, final String community, final String keyword) {
        this.title = title;
        this.link = link;
        this.community = community;
        this.keyword = keyword;
    }

    public Posts toEntity() {
        return Posts.builder()
            .title(title)
            .link(link)
            .community(community)
            .keyword(keyword)
            .build();
    }
}
