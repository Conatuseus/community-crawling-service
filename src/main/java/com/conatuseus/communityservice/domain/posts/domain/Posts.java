package com.conatuseus.communityservice.domain.posts.domain;

import com.conatuseus.communityservice.domain.base.domain.BaseTimeEntity;
import com.conatuseus.communityservice.domain.community.domain.Community;
import com.conatuseus.communityservice.domain.posts.service.dto.PostsUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POSTS_ID")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String link;

    @ManyToOne
    @JoinColumn(name = "COMMUNITY_ID")
    private Community community;

    @Column(nullable = false)
    private String keyword;

    @Builder
    public Posts(final String title, final String link, final Community community, final String keyword) {
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
