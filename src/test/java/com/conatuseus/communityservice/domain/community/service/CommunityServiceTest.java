package com.conatuseus.communityservice.domain.community.service;

import com.conatuseus.communityservice.domain.community.domain.Community;
import com.conatuseus.communityservice.domain.community.domain.CommunityRepository;
import com.conatuseus.communityservice.domain.community.service.dto.CommunityResponse;
import com.conatuseus.communityservice.domain.community.service.dto.CommunitySaveRequestDto;
import com.conatuseus.communityservice.domain.community.service.dto.CommunityUpdateRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = CommunityService.class)
class CommunityServiceTest {

    private CommunityService communityService;

    @MockBean
    private CommunityRepository communityRepository;

    @BeforeEach
    public void setup() {
        communityService = new CommunityService(communityRepository);
    }

    @Test
    void save() {
        //given
        CommunitySaveRequestDto requestDto = CommunitySaveRequestDto.builder()
            .name("okky")
            .searchUrl("https://okky.kr/articles/gathering?query=%EC%84%B1%EB%82%A8&sort=id&order=desc")
            .keyword("성남")
            .cssQuery("h5.list-group-item-heading.list-group-item-evaluate > a")
            .attributeKey("href")
            .build();

        given(communityRepository.save(any())).willReturn(Community.builder()
            .name("okky")
            .searchUrl("https://okky.kr/articles/gathering?query=%EC%84%B1%EB%82%A8&sort=id&order=desc")
            .keyword("성남")
            .cssQuery("h5.list-group-item-heading.list-group-item-evaluate > a")
            .attributeKey("href")
            .build());

        //when
        CommunityResponse response = communityService.save(requestDto);

        //then
        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo(requestDto.getName());
        assertThat(response.getSearchUrl()).isEqualTo(requestDto.getSearchUrl());
    }

    @Test
    void findById() {
        //given
        Long communityId = 1L;
        Community community = Community.builder()
            .name("okky")
            .searchUrl("https://okky.kr/articles/gathering?query=%EC%84%B1%EB%82%A8&sort=id&order=desc")
            .keyword("성남")
            .cssQuery("h5.list-group-item-heading.list-group-item-evaluate > a")
            .attributeKey("href")
            .build();

        given(communityRepository.findById(anyLong())).willReturn(of(community));

        //when
        CommunityResponse response = communityService.findById(communityId);

        //then
        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo(community.getName());
        assertThat(response.getKeyword()).isEqualTo(community.getKeyword());
    }

    @Test
    void update() {
        //given
        Community community = Community.builder()
            .name("okky")
            .searchUrl("https://okky.kr/articles/gathering?query=%EC%84%B1%EB%82%A8&sort=id&order=desc")
            .keyword("성남")
            .cssQuery("h5.list-group-item-heading.list-group-item-evaluate > a")
            .attributeKey("href")
            .build();

        Long communityId = 1L;
        CommunityUpdateRequestDto requestDto = CommunityUpdateRequestDto.builder()
            .name("updatedName")
            .keyword("updatedKeyword")
            .searchUrl("updatedSearchUrl")
            .attributeKey("updatedAttributeKey")
            .cssQuery("updatedCssQuery")
            .build();

        given(communityRepository.findById(anyLong())).willReturn(ofNullable(community));

        //when
        CommunityResponse updatedResponse = communityService.update(1L, requestDto);

        //then
        assertThat(updatedResponse).isNotNull();
        assertThat(updatedResponse.getName()).isEqualTo("updatedName");
        assertThat(updatedResponse.getKeyword()).isEqualTo("updatedKeyword");
    }

    @Test
    void deleteById() {
        //given
        Long communityId = 1L;

        Community community = Community.builder()
            .name("okky")
            .searchUrl("https://okky.kr/articles/gathering?query=%EC%84%B1%EB%82%A8&sort=id&order=desc")
            .keyword("성남")
            .cssQuery("h5.list-group-item-heading.list-group-item-evaluate > a")
            .attributeKey("href")
            .build();

        given(communityRepository.findById(anyLong())).willReturn(ofNullable(community));

        //when&then
        assertDoesNotThrow(() -> communityService.deleteById(1L));
    }
}