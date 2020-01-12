package com.conatuseus.communityservice.domain.posts.domain;

import com.conatuseus.communityservice.domain.posts.service.dto.PostsUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private String community;

    @Column(nullable = false)
    private String keyword;

    @Builder
    public Posts(final String title, final String link, final String community, final String keyword) {
        this.title = title;
        this.link = link;
        this.community = community;
        this.keyword = keyword;
    }

    public Posts update(final PostsUpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.link = requestDto.getLink();
        return this;
    }
}
