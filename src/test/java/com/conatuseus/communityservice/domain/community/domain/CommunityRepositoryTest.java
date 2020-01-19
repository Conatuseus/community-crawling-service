package com.conatuseus.communityservice.domain.community.domain;

import com.conatuseus.communityservice.domain.community.service.dto.CommunityUpdateRequestDto;
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
        String baseUrl = "https://okky.kr";
        String searchUrl = "https://okky.kr/articles/gathering?query=%EC%84%B1%EB%82%A8&sort=id&order=desc";
        String keyword = "성남";
        String cssQuery = "h5.list-group-item-heading.list-group-item-evaluate > a";
        String attributeKey = "href";

        Community community = Community.builder()
            .name(name)
            .baseUrl(baseUrl)
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

    @Test
    public void findById() {
        //given
        String name = "okky";
        String baseUrl = "https://okky.kr";
        String searchUrl = "https://okky.kr/articles/gathering?query=%EC%84%B1%EB%82%A8&sort=id&order=desc";
        String keyword = "성남";
        String cssQuery = "h5.list-group-item-heading.list-group-item-evaluate > a";
        String attributeKey = "href";

        Community community = communityRepository.save(Community.builder()
            .name(name)
            .baseUrl(baseUrl)
            .searchUrl(searchUrl)
            .keyword(keyword)
            .cssQuery(cssQuery)
            .attributeKey(attributeKey)
            .build());

        //when
        Community found = communityRepository.findById(community.getId())
            .orElse(null);

        //then
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo(name);
        assertThat(found.getKeyword()).isEqualTo(keyword);
        assertThat(found.getCssQuery()).isEqualTo(cssQuery);
    }

    @Test
    public void update() {
        //given
        String name = "okky";
        String baseUrl = "https://okky.kr";
        String searchUrl = "https://okky.kr/articles/gathering?query=%EC%84%B1%EB%82%A8&sort=id&order=desc";
        String keyword = "성남";
        String cssQuery = "h5.list-group-item-heading.list-group-item-evaluate > a";
        String attributeKey = "href";

        Community community = communityRepository.save(Community.builder()
            .name(name)
            .baseUrl(baseUrl)
            .searchUrl(searchUrl)
            .keyword(keyword)
            .cssQuery(cssQuery)
            .attributeKey(attributeKey)
            .build());

        //when
        community.update(CommunityUpdateRequestDto.builder()
            .name("updatedName")
            .baseUrl("updatedBaseUrl")
            .searchUrl("updatedUrl")
            .keyword("updatedKeyword")
            .cssQuery("updatedCssQuery")
            .attributeKey("updatedAttributeKey")
            .build());

        //then
        assertThat(community).isNotNull();
        assertThat(community.getName()).isEqualTo("updatedName");
        assertThat(community.getKeyword()).isEqualTo("updatedKeyword");
        assertThat(community.getCssQuery()).isEqualTo("updatedCssQuery");
    }

    @Test
    public void delete() {
        //given
        String name = "okky";
        String baseUrl = "https://okky.kr";
        String searchUrl = "https://okky.kr/articles/gathering?query=%EC%84%B1%EB%82%A8&sort=id&order=desc";
        String keyword = "성남";
        String cssQuery = "h5.list-group-item-heading.list-group-item-evaluate > a";
        String attributeKey = "href";

        Community community = communityRepository.save(Community.builder()
            .name(name)
            .baseUrl(baseUrl)
            .searchUrl(searchUrl)
            .keyword(keyword)
            .cssQuery(cssQuery)
            .attributeKey(attributeKey)
            .build());

        //when
        communityRepository.delete(community);
        List<Community> communities = communityRepository.findAll();

        //then
        assertThat(communities.contains(community)).isFalse();
    }
}