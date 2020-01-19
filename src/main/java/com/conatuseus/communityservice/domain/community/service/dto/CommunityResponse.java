package com.conatuseus.communityservice.domain.community.service.dto;

import com.conatuseus.communityservice.domain.community.domain.Community;
import lombok.Getter;

@Getter
public class CommunityResponse {

    private final Long id;
    private final String name;
    private final String baseUrl;
    private final String searchUrl;
    private final String keyword;
    private final String cssQuery;
    private final String attributeKey;

    public CommunityResponse(final Community community) {
        this.id = community.getId();
        this.name = community.getName();
        this.baseUrl = community.getBaseUrl();
        this.searchUrl = community.getSearchUrl();
        this.keyword = community.getKeyword();
        this.cssQuery = community.getCssQuery();
        this.attributeKey = community.getAttributeKey();
    }
}
