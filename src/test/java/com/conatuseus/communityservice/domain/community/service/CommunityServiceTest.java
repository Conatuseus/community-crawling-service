package com.conatuseus.communityservice.domain.community.service;

import com.conatuseus.communityservice.domain.community.domain.Community;
import com.conatuseus.communityservice.domain.community.domain.CommunityRepository;
import com.conatuseus.communityservice.domain.community.service.dto.CommunityResponse;
import com.conatuseus.communityservice.domain.community.service.dto.CommunitySaveRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
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
}