package com.conatuseus.communityservice.domain.posts.service;

import com.conatuseus.communityservice.domain.community.domain.Community;
import com.conatuseus.communityservice.domain.community.service.CommunityService;
import com.conatuseus.communityservice.domain.posts.domain.Posts;
import com.conatuseus.communityservice.domain.posts.domain.PostsRepository;
import com.conatuseus.communityservice.domain.posts.service.dto.PostsResponse;
import com.conatuseus.communityservice.domain.posts.service.dto.PostsSaveRequestDto;
import com.conatuseus.communityservice.domain.posts.service.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class PostsService {

    private final CommunityService communityService;
    private final PostsRepository postsRepository;

    public PostsResponse save(final String communityName, final PostsSaveRequestDto requestDto) {
        Community community = communityService.findByName(communityName, requestDto.getKeyword());
        Posts posts = postsRepository.save(requestDto.toEntity(community));
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

    @Transactional(readOnly = true)
    public List<PostsResponse> findByCommunityNameAndKeyword(final String communityName, final String keyword) {
        Community community = communityService.findByName(communityName, keyword);
        return postsRepository.findByCommunityAndKeywordOrderByModifiedDate(community, keyword).stream()
            .map(PostsResponse::new)
            .collect(Collectors.toList());
    }
}
