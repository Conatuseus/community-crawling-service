package com.conatuseus.communityservice.domain.community.domain;

import com.conatuseus.communityservice.domain.community.service.dto.CommunityUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CommunityRepositoryTest {

    @Autowired
    private CommunityRepository communityRepository;

    @Test
    public void save() {
        //given
        String name = "testName11";
        String baseUrl = "testBaseUrl";
        String searchUrl = "testSearchUrl";
        String keyword = "testKeyword";
        String cssQuery = "testCssQuery";
        String attributeKey = "testAttributeKey";

        Community community = Community.builder()
            .name(name)
            .baseUrl(baseUrl)
            .searchUrl(searchUrl)
            .keyword(keyword)
            .cssQuery(cssQuery)
            .attributeKey(attributeKey)
            .build();

        //when
        Community savedCommunity = communityRepository.save(community);
        Optional<Community> foundCommunity= communityRepository.findById(savedCommunity.getId());

        //then
        assertThat(foundCommunity).isNotEmpty();
        assertThat(foundCommunity.get().getName()).isEqualTo(name);
        assertThat(foundCommunity.get().getSearchUrl()).isEqualTo(searchUrl);
        assertThat(foundCommunity.get().getKeyword()).isEqualTo(keyword);
    }

    @Test
    public void findById() {
        //given
        String name = "testName22";
        String baseUrl = "testBaseUrl";
        String searchUrl = "testSearchUrl";
        String keyword = "testKeyword";
        String cssQuery = "testCssQuery";
        String attributeKey = "testAttributeKey";

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
        String name = "testName33";
        String baseUrl = "testBaseUrl";
        String searchUrl = "testSearchUrl";
        String keyword = "testKeyword";
        String cssQuery = "testCssQuery";
        String attributeKey = "testAttributeKey";

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
        String name = "testName44";
        String baseUrl = "testBaseUrl";
        String searchUrl = "testSearchUrl";
        String keyword = "testKeyword";
        String cssQuery = "testCssQuery";
        String attributeKey = "testAttributeKey";

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