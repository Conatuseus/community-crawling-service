package com.conatuseus.communityservice.domain.community.domain;

import com.conatuseus.communityservice.domain.community.service.dto.CommunityUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String searchUrl;

    private String keyword;

    private String cssQuery;

    private String attributeKey;

    @Builder
    public Community(final String name, final String searchUrl, final String keyword, final String cssQuery, final String attributeKey) {
        this.name = name;
        this.searchUrl = searchUrl;
        this.keyword = keyword;
        this.cssQuery = cssQuery;
        this.attributeKey = attributeKey;
    }

    public Community update(final CommunityUpdateRequestDto requestDto) {
        this.name = requestDto.getName();
        this.searchUrl = requestDto.getSearchUrl();
        this.keyword = requestDto.getKeyword();
        this.cssQuery = requestDto.getCssQuery();
        this.attributeKey = requestDto.getAttributeKey();
        return this;
    }
}
