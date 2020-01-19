package com.conatuseus.communityservice.domain.posts.service;

import com.conatuseus.communityservice.domain.community.domain.Community;
import com.conatuseus.communityservice.domain.community.service.CommunityService;
import com.conatuseus.communityservice.domain.posts.domain.Posts;
import com.conatuseus.communityservice.domain.posts.domain.PostsRepository;
import com.conatuseus.communityservice.domain.posts.service.dto.PostsResponse;
import com.conatuseus.communityservice.domain.posts.service.dto.PostsSaveRequestDto;
import com.conatuseus.communityservice.domain.posts.service.dto.PostsUpdateRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static java.util.Optional.ofNullable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = PostsService.class)
class PostsServiceTest {

    private PostsService postsService;

    @MockBean
    private PostsRepository postsRepository;

    @MockBean
    private CommunityService communityService;

    private Community community;

    @BeforeEach
    void setup() {
        postsService = new PostsService(communityService, postsRepository);
        community = Community.builder()
            .name("OKKY")
            .baseUrl("https://okky.kr")
            .attributeKey("attributeKey")
            .cssQuery("cssQuery")
            .keyword("keyword")
            .searchUrl("searchUrl")
            .build();
    }

    @Test
    void create_posts() {
        //given
        Community community = Community.builder()
            .name("OKKY")
            .baseUrl("https://okky.kr")
            .attributeKey("attributeKey")
            .cssQuery("cssQuery")
            .keyword("keyword")
            .searchUrl("searchUrl")
            .build();

        PostsSaveRequestDto requestDto = new PostsSaveRequestDto("testTitle", "testLink", "성남");
        given(communityService.findByName("OKKY")).willReturn(community);
        given(postsRepository.save(any())).willReturn(new Posts("testTitle", "testLink", community, "성남"));

        //when
        PostsResponse response = postsService.save("OKKY", requestDto);

        //then
        assertThat(response).isNotNull();
        assertThat(response.getTitle()).isEqualTo(requestDto.getTitle());
        assertThat(response.getLink()).isEqualTo(requestDto.getLink());
    }

    @Test
    void find_by_id_posts() {
        //given
        Posts expected = Posts.builder()
            .title("testTitle")
            .link("testLink")
            .community(community)
            .keyword("성남")
            .build();
        given(postsRepository.findById(anyLong())).willReturn(ofNullable(expected));

        //when
        PostsResponse postsResponse = postsService.findById(1L);

        //then
        assertThat(postsResponse).isNotNull();
        assertThat(postsResponse.getTitle()).isEqualTo(expected.getTitle());
        assertThat(postsResponse.getLink()).isEqualTo(expected.getLink());
    }

    @Test
    void update_posts() {
        //given
        Posts posts = Posts.builder()
            .title("testTitle")
            .link("testLink")
            .community(community)
            .keyword("성남")
            .build();

        PostsUpdateRequestDto requestDto = new PostsUpdateRequestDto("updatedTitle", "updatedLink");

        given(postsRepository.findById(anyLong())).willReturn(ofNullable(posts));

        //when
        PostsResponse postsResponse = postsService.update(1L, requestDto);

        //then
        assertThat(postsResponse).isNotNull();
        assertThat(postsResponse.getTitle()).isEqualTo(requestDto.getTitle());
        assertThat(postsResponse.getLink()).isEqualTo(requestDto.getLink());
    }

    @Test
    void delete_posts() {
        //given
        Posts deletePosts = Posts.builder()
            .title("testTitle")
            .link("testLink")
            .community(community)
            .keyword("성남")
            .build();
        given(postsRepository.findById(anyLong())).willReturn(ofNullable(deletePosts));

        //when&then
        assertDoesNotThrow(() -> postsService.deleteById(1L));
    }

    @Test
    void exists_by_link() {
        //given
        given(postsRepository.existsByLink(any())).willReturn(true);

        //when&then
        assertThat(postsService.existsByLink("testLink")).isTrue();
    }
}