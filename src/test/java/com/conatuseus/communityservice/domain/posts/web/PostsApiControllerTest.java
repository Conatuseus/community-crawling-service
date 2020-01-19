package com.conatuseus.communityservice.domain.posts.web;

import com.conatuseus.communityservice.domain.community.service.CommunityService;
import com.conatuseus.communityservice.domain.posts.service.dto.PostsSaveRequestDto;
import com.conatuseus.communityservice.domain.posts.service.dto.PostsUpdateRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;


@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    private static final String baseUrl = "/api/v1/posts";

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private CommunityService communityService;

    @Test
    void createPosts() {
        //given
        PostsSaveRequestDto requestDto = new PostsSaveRequestDto("title", "link", "성남");

        //when&then
        save(requestDto)
            .expectStatus().isCreated();
    }

    @Test
    void findById() {
        //given
        PostsSaveRequestDto requestDto = new PostsSaveRequestDto("title", "link", "성남");
        EntityExchangeResult<byte[]> entityExchangeResult = save(requestDto)
            .expectHeader().valueMatches("Location", PostsApiController.V1_POSTS + "/\\d*")
            .expectBody().returnResult();

        //when&then
        webTestClient.get()
            .uri(entityExchangeResult.getResponseHeaders().getLocation().toASCIIString())
            .exchange()
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.title").isEqualTo(requestDto.getTitle())
            .jsonPath("$.link").isEqualTo(requestDto.getLink());
    }

    @Test
    void update() {
        //given
        PostsSaveRequestDto saveRequestDto = new PostsSaveRequestDto("title", "link", "성남");
        EntityExchangeResult<byte[]> entityExchangeResult = save(saveRequestDto)
            .expectHeader().valueMatches("Location", PostsApiController.V1_POSTS + "/\\d*")
            .expectBody().returnResult();

        PostsUpdateRequestDto updateRequestDto = new PostsUpdateRequestDto("updatedTitle", "updatedLink");

        //when&then
        webTestClient.put()
            .uri(entityExchangeResult.getResponseHeaders().getLocation().toASCIIString())
            .body(Mono.just(updateRequestDto), PostsUpdateRequestDto.class)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.title").isEqualTo(updateRequestDto.getTitle())
            .jsonPath("$.link").isEqualTo(updateRequestDto.getLink());
    }

    @Test
    void deleteById() {
        //given
        PostsSaveRequestDto saveRequestDto = new PostsSaveRequestDto("title", "link", "성남");
        EntityExchangeResult<byte[]> entityExchangeResult = save(saveRequestDto)
            .expectHeader().valueMatches("Location", PostsApiController.V1_POSTS + "/\\d*")
            .expectBody().returnResult();

        //when&then
        webTestClient.delete()
            .uri(entityExchangeResult.getResponseHeaders().getLocation().toASCIIString())
            .exchange()
            .expectStatus().isNoContent();
    }

    private WebTestClient.ResponseSpec save(final PostsSaveRequestDto requestDto) {
        return webTestClient.post()
            .uri(baseUrl + "/OKKY")
            .body(Mono.just(requestDto), PostsSaveRequestDto.class)
            .exchange();
    }
}