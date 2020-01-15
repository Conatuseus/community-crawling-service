package com.conatuseus.communityservice.domain.posts.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanUp() {
        postsRepository.deleteAll();
    }

    @Test
    public void save() {
        //given
        String title = "title";
        String link = "link";
        String community = "okky";
        String keyword = "성남";

        Posts posts = Posts.builder()
            .title(title)
            .link(link)
            .community(community)
            .keyword(keyword)
            .build();

        //when
        postsRepository.save(posts);
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts savedPosts = postsList.get(0);
        assertThat(savedPosts.getTitle()).isEqualTo(title);
        assertThat(savedPosts.getLink()).isEqualTo(link);
    }

    @Test
    public void findById() {
        //given
        Posts posts = postsRepository.save(Posts.builder()
            .title("title")
            .link("link")
            .community("okky")
            .keyword("성남")
            .build());

        //when
        Posts foundPosts = postsRepository.findById(posts.getId())
            .orElse(null);

        //then
        assertThat(foundPosts).isNotNull();
        assertThat(foundPosts.getTitle()).isEqualTo(posts.getTitle());
        assertThat(foundPosts.getLink()).isEqualTo(posts.getLink());
    }

    @Test
    public void delete() {
        //given
        Posts posts = postsRepository.save(Posts.builder()
            .title("title")
            .link("link")
            .community("okky")
            .keyword("성남")
            .build());

        //when
        postsRepository.delete(posts);
        List<Posts> postsList = postsRepository.findAll();

        //then
        assertThat(postsList.contains(posts)).isFalse();
    }

    @Test
    @DisplayName("link가 존재하는 경우")
    public void exists_by_link() {
        //given
        Posts posts = postsRepository.save(Posts.builder()
            .title("title")
            .link("link")
            .community("okky")
            .keyword("성남")
            .build());
        postsRepository.save(posts);

        //when
        boolean existsByLink = postsRepository.existsByLink("link");

        //then
        assertThat(existsByLink).isTrue();
    }

    @Test
    @DisplayName("link가 존재하지 않는 경우")
    public void not_exists_by_link() {
        //given
        Posts posts = postsRepository.save(Posts.builder()
            .title("title")
            .link("link")
            .community("okky")
            .keyword("성남")
            .build());
        postsRepository.save(posts);

        //when
        boolean existsByLink = postsRepository.existsByLink("testLink");

        //then
        assertThat(existsByLink).isFalse();
    }

    @Test
    @DisplayName("JpaAuditing 동작하는지 검사")
    public void baseEntity() {
        //given
        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(Posts.builder()
            .title("title")
            .link("link")
            .community("okky")
            .keyword("성남")
            .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}