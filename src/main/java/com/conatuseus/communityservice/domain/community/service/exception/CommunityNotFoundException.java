package com.conatuseus.communityservice.domain.community.service.exception;

public class CommunityNotFoundException extends RuntimeException {

    private static final String COMMUNITY_NOT_FOUND_MESSAGE = "해당 커뮤니티를 찾을 수 없습니다. id = ";

    public CommunityNotFoundException(final Long communityId) {
        super(COMMUNITY_NOT_FOUND_MESSAGE + communityId);
    }
}
