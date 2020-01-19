package com.conatuseus.communityservice.domain.community.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommunityUpdateRequestDto {

    private String name;
    private String baseUrl;
    private String searchUrl;
    private String keyword;
    private String cssQuery;
    private String attributeKey;

    @Builder
    public CommunityUpdateRequestDto(final String name, final String baseUrl, final String searchUrl, final String keyword, final String cssQuery, final String attributeKey) {
        this.name = name;
        this.baseUrl = baseUrl;
        this.searchUrl = searchUrl;
        this.keyword = keyword;
        this.cssQuery = cssQuery;
        this.attributeKey = attributeKey;
    }
}
