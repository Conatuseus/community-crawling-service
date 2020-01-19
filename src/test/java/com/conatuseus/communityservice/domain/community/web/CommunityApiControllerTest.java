package com.conatuseus.communityservice.domain.community.web;

import com.conatuseus.communityservice.domain.community.service.dto.CommunitySaveRequestDto;
import com.conatuseus.communityservice.domain.community.service.dto.CommunityUpdateRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommunityApiControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    private String baseUrl = "/api/v1/communities";
    private CommunitySaveRequestDto requestDto = CommunitySaveRequestDto.builder()
        .name("okky")
        .baseUrl("https://okky.kr")
        .searchUrl("https://okky.kr/articles/gathering?query=%EC%84%B1%EB%82%A8&sort=id&order=desc")
        .keyword("성남")
        .cssQuery("h5.list-group-item-heading.list-group-item-evaluate > a")
        .attributeKey("href")
        .build();

    @Test
    public void save() {
        //when & then
        save(requestDto)
            .expectStatus().isCreated();
    }

    @Test
    public void findById() {
        //given
        EntityExchangeResult<byte[]> exchangeResult = save(requestDto)
            .expectHeader().valueMatches("Location", CommunityApiController.V1_COMMUNITY + "/\\d*")
            .expectBody().returnResult();
        String findUrl = exchangeResult.getResponseHeaders().getLocation().toASCIIString();

        //when&then
        webTestClient.get()
            .uri(findUrl)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.name").isEqualTo("okky")
            .jsonPath("$.keyword").isEqualTo("성남")
            .jsonPath("$.attributeKey").isEqualTo("href");
    }

    @Test
    public void update() {
        //given
        EntityExchangeResult<byte[]> exchangeResult = save(requestDto)
            .expectHeader().valueMatches("Location", CommunityApiController.V1_COMMUNITY + "/\\d*")
            .expectBody().returnResult();

        CommunityUpdateRequestDto requestDto = CommunityUpdateRequestDto.builder()
            .name("updatedName")
            .baseUrl("updatedBaseUrl")
            .searchUrl("updatedUrl")
            .keyword("updatedKeyword")
            .cssQuery("updatedCssQuery")
            .attributeKey("updatedAttributeKey")
            .build();

        //when&then
        webTestClient.put()
            .uri(exchangeResult.getResponseHeaders().getLocation().toASCIIString())
            .body(Mono.just(requestDto), CommunityUpdateRequestDto.class)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.name").isEqualTo("updatedName")
            .jsonPath("$.searchUrl").isEqualTo("updatedUrl")
            .jsonPath("$.keyword").isEqualTo("updatedKeyword")
            .jsonPath("$.cssQuery").isEqualTo("updatedCssQuery");
    }

    @Test
    public void deleteById() {
        //given
        EntityExchangeResult<byte[]> exchangeResult = save(requestDto)
            .expectHeader().valueMatches("Location", CommunityApiController.V1_COMMUNITY + "/\\d*")
            .expectBody().returnResult();

        //when&then
        webTestClient.delete()
            .uri(exchangeResult.getResponseHeaders().getLocation().toASCIIString())
            .exchange()
            .expectStatus().isNoContent();

    }

    private WebTestClient.ResponseSpec save(final CommunitySaveRequestDto requestDto) {
        return webTestClient.post()
            .uri(baseUrl)
            .body(Mono.just(requestDto), CommunitySaveRequestDto.class)
            .exchange();
    }
}