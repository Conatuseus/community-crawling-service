package com.conatuseus.communityservice.domain.posts.controller;

import com.conatuseus.communityservice.domain.posts.service.PostsService;
import com.conatuseus.communityservice.domain.posts.service.dto.PostsResponse;
import com.conatuseus.communityservice.domain.posts.service.dto.PostsSaveRequestDto;
import com.conatuseus.communityservice.domain.posts.service.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.conatuseus.communityservice.domain.posts.controller.PostsApiController.V1_POSTS;

@RequiredArgsConstructor
@RequestMapping(V1_POSTS)
@RestController
public class PostsApiController {

    public static final String V1_POSTS = "/api/v1/posts";
    private final PostsService postsService;

    @PostMapping
    public ResponseEntity save(@RequestBody final PostsSaveRequestDto requestDto) {
        PostsResponse response = postsService.save(requestDto);

        return ResponseEntity.created(URI.create(V1_POSTS + "/" + response.getId()))
            .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostsResponse> findById(@PathVariable final Long id) {
        PostsResponse response = postsService.findById(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostsResponse> update(@PathVariable final Long id, @RequestBody final PostsUpdateRequestDto requestDto) {
        PostsResponse response = postsService.update(id, requestDto);

        return ResponseEntity.ok(response);
    }
}
