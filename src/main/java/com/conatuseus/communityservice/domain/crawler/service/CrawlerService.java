package com.conatuseus.communityservice.domain.crawler.service;

import com.conatuseus.communityservice.domain.community.domain.Community;
import com.conatuseus.communityservice.domain.community.service.CommunityService;
import com.conatuseus.communityservice.domain.crawler.domain.Crawler;
import com.conatuseus.communityservice.domain.crawler.service.exception.InvalidTitleLinkSizeException;
import com.conatuseus.communityservice.domain.posts.service.PostsService;
import com.conatuseus.communityservice.domain.posts.service.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CrawlerService {

    private final CommunityService communityService;
    private final PostsService postsService;

    @Transactional
    public void crawling() throws IOException {
        List<Community> communities = communityService.findAll();

        for (Community community : communities) {
            Crawler crawler = new Crawler(community);
            List<String> titles = crawler.getTitles();
            List<String> links = crawler.getLinks();

            if (titles.size() != links.size()) {
                throw new InvalidTitleLinkSizeException(titles.size(), links.size());
            }
            savePosts(community, titles, links);
        }
    }

    private void savePosts(final Community community, final List<String> titles, final List<String> links) {
        for (int i = 0; i < titles.size(); i++) {
            String title = titles.get(i);
            String link = links.get(i);

            if (postsService.existsByLink(link)) {
                break;
            }
            PostsSaveRequestDto requestDto = new PostsSaveRequestDto(title, link, community.getName(), community.getKeyword());
            postsService.save(requestDto);
        }
    }
}
