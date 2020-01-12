package com.conatuseus.communityservice.domain.posts.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}