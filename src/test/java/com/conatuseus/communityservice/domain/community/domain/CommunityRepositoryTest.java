package com.conatuseus.communityservice.domain.community.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CommunityRepositoryTest {

    @Autowired
    private CommunityRepository communityRepository;

    @AfterEach
    public void cleanUp() {
        communityRepository.deleteAll();
    }

    @Test
    public void save() {
        //given
        String name = "okky";
        String searchUrl = "https://okky.kr/articles/gathering?query=%EC%84%B1%EB%82%A8&sort=id&order=desc";
        String keyword = "성남";
        String cssQuery = "h5.list-group-item-heading.list-group-item-evaluate > a";
        String attributeKey = "href";

        Community community = Community.builder()
            .name(name)
            .searchUrl(searchUrl)
            .keyword(keyword)
            .cssQuery(cssQuery)
            .attributeKey(attributeKey)
            .build();

        //when
        communityRepository.save(community);
        List<Community> communities = communityRepository.findAll();

        //then
        Community savedCommunity = communities.get(0);
        assertThat(savedCommunity.getName()).isEqualTo(name);
        assertThat(savedCommunity.getSearchUrl()).isEqualTo(searchUrl);
        assertThat(savedCommunity.getKeyword()).isEqualTo(keyword);
    }
}