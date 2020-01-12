package com.conatuseus.communityservice.domain.posts.controller;

import com.conatuseus.communityservice.domain.posts.service.dto.PostsSaveRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;


@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    private String baseUrl = "/api/v1/posts";

    @Test
    void createPosts() {
        //given
        PostsSaveRequestDto requestDto = new PostsSaveRequestDto("title", "link", "okky", "성남");

        //when&then
        save(requestDto)
            .expectStatus().isCreated();
    }

    private WebTestClient.ResponseSpec save(final PostsSaveRequestDto requestDto) {
        return webTestClient.post()
            .uri(baseUrl)
            .body(Mono.just(requestDto), PostsSaveRequestDto.class)
            .exchange();
    }
}