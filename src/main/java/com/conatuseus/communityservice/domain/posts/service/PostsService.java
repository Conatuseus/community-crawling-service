package com.conatuseus.communityservice.domain.posts.service;

import com.conatuseus.communityservice.domain.posts.domain.Posts;
import com.conatuseus.communityservice.domain.posts.domain.PostsRepository;
import com.conatuseus.communityservice.domain.posts.service.dto.PostsResponse;
import com.conatuseus.communityservice.domain.posts.service.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    public PostsResponse save(final PostsSaveRequestDto requestDto) {
        Posts posts = postsRepository.save(requestDto.toEntity());
        return new PostsResponse(posts);
    }
}
