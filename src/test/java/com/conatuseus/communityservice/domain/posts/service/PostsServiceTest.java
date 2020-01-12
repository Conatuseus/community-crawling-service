package com.conatuseus.communityservice.domain.posts.service;

import com.conatuseus.communityservice.domain.posts.domain.Posts;
import com.conatuseus.communityservice.domain.posts.domain.PostsRepository;
import com.conatuseus.communityservice.domain.posts.service.dto.PostsResponse;
import com.conatuseus.communityservice.domain.posts.service.dto.PostsSaveRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = PostsService.class)
class PostsServiceTest {

    private PostsService postsService;

    @MockBean
    private PostsRepository postsRepository;

    @BeforeEach
    void setup() {
        postsService = new PostsService(postsRepository);
    }

    @Test
    void create_posts() {
        //given
        PostsSaveRequestDto requestDto = new PostsSaveRequestDto("testTitle", "testLink", "okky", "성남");
        given(postsRepository.save(any())).willReturn(new Posts("testTitle", "testLink", "okky", "성남"));

        //when
        PostsResponse response = postsService.save(requestDto);

        //then
        assertThat(response).isNotNull();
        assertThat(response.getTitle()).isEqualTo(requestDto.getTitle());
        assertThat(response.getLink()).isEqualTo(requestDto.getLink());
    }
}