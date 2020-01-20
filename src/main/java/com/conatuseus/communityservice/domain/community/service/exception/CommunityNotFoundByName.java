package com.conatuseus.communityservice.domain.community.service.exception;

public class CommunityNotFoundByName extends RuntimeException {

    private static final String COMMUNITY_NOT_FOUND_BY_NAME_MESSAGE = "해당 이름의 커뮤니티가 존재하지 않습니다.: ";

    public CommunityNotFoundByName(final String name, final String keyword) {
        super(String.format("%s %s:%s", COMMUNITY_NOT_FOUND_BY_NAME_MESSAGE, name, keyword));
    }
}
