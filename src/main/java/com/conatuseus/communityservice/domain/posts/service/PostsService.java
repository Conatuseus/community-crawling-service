package com.conatuseus.communityservice.domain.posts.service;

import com.conatuseus.communityservice.domain.posts.domain.Posts;
import com.conatuseus.communityservice.domain.posts.domain.PostsRepository;
import com.conatuseus.communityservice.domain.posts.service.dto.PostsResponse;
import com.conatuseus.communityservice.domain.posts.service.dto.PostsSaveRequestDto;
import com.conatuseus.communityservice.domain.posts.service.dto.PostsUpdateRequestDto;
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

    @Transactional(readOnly = true)
    public PostsResponse findById(final Long id) {
        Posts posts = postsRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 글은 존재하지 않습니다."));

        return new PostsResponse(posts);
    }

    public PostsResponse update(final Long id, final PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 글은 존재하지 않습니다."));

        return new PostsResponse(posts.update(requestDto));
    }

    public void deleteById(final Long id) {
        Posts posts = postsRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 글은 존재하지 않습니다."));

        postsRepository.delete(posts);
    }

    @Transactional(readOnly = true)
    public boolean existsByLink(final String link) {
        return postsRepository.existsByLink(link);
    }
}
