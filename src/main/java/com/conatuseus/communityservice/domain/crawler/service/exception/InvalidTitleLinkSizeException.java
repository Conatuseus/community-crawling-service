package com.conatuseus.communityservice.domain.crawler.service.exception;

public class InvalidTitleLinkSizeException extends RuntimeException {

    private static final String INVALID_TITLE_AND_LINK_SIZE_MESSAGE = "title과 link의 크기가 다릅니다. ";
    public static final String TITLE = "title: ";
    public static final String LINK = "link: ";


    public InvalidTitleLinkSizeException(final int titleSize, final int linkSize) {
        super(String.format("%s %s %d %s %d", INVALID_TITLE_AND_LINK_SIZE_MESSAGE, TITLE, titleSize, LINK, linkSize));
    }
}
