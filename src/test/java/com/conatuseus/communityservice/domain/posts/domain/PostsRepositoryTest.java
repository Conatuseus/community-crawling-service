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
}