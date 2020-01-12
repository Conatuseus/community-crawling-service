package com.conatuseus.communityservice.domain.community.service.dto;

import com.conatuseus.communityservice.domain.community.domain.Community;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommunitySaveRequestDto {

    private String name;
    private String searchUrl;
    private String keyword;
    private String cssQuery;
    private String attributeKey;

    @Builder
    public CommunitySaveRequestDto(final String name, final String searchUrl, final String keyword, final String cssQuery, final String attributeKey) {
        this.name = name;
        this.searchUrl = searchUrl;
        this.keyword = keyword;
        this.cssQuery = cssQuery;
        this.attributeKey = attributeKey;
    }

    public Community toEntity() {
        return Community.builder()
            .name(name)
            .searchUrl(searchUrl)
            .keyword(keyword)
            .cssQuery(cssQuery)
            .attributeKey(attributeKey)
            .build();
    }
}
