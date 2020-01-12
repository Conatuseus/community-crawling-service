package com.conatuseus.communityservice.domain.community.controller;

import com.conatuseus.communityservice.domain.community.service.dto.CommunitySaveRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommunityApiControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    private String baseUrl = "/api/v1/communities";

    @Test
    public void save() {
        //given
        CommunitySaveRequestDto requestDto = CommunitySaveRequestDto.builder()
            .name("okky")
            .searchUrl("https://okky.kr/articles/gathering?query=%EC%84%B1%EB%82%A8&sort=id&order=desc")
            .keyword("성남")
            .cssQuery("h5.list-group-item-heading.list-group-item-evaluate > a")
            .attributeKey("href")
            .build();

        //when
        save(requestDto)
            .expectStatus().isCreated();
    }

    private WebTestClient.ResponseSpec save(final CommunitySaveRequestDto requestDto) {
        return webTestClient.post()
            .uri(baseUrl)
            .body(Mono.just(requestDto), CommunitySaveRequestDto.class)
            .exchange();
    }
}