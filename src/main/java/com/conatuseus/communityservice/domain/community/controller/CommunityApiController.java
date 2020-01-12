package com.conatuseus.communityservice.domain.community.controller;

import com.conatuseus.communityservice.domain.community.service.CommunityService;
import com.conatuseus.communityservice.domain.community.service.dto.CommunityResponse;
import com.conatuseus.communityservice.domain.community.service.dto.CommunitySaveRequestDto;
import com.conatuseus.communityservice.domain.community.service.dto.CommunityUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.conatuseus.communityservice.domain.community.controller.CommunityApiController.V1_COMMUNITY;

@RequiredArgsConstructor
@RequestMapping(V1_COMMUNITY)
@RestController
public class CommunityApiController {

    public static final String V1_COMMUNITY = "/api/v1/communities";
    private final CommunityService communityService;

    @PostMapping
    public ResponseEntity save(@RequestBody final CommunitySaveRequestDto requestDto) {
        CommunityResponse response = communityService.save(requestDto);

        return ResponseEntity.created(URI.create(V1_COMMUNITY + "/" + response.getId())).build();
    }

    @GetMapping("/{communityId}")
    public ResponseEntity<CommunityResponse> findById(@PathVariable final Long communityId) {
        CommunityResponse response = communityService.findById(communityId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{communityId}")
    public ResponseEntity<CommunityResponse> update(@PathVariable final Long communityId, @RequestBody final CommunityUpdateRequestDto requestDto) {
        CommunityResponse response = communityService.update(communityId, requestDto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{communityId}")
    public ResponseEntity deleteById(@PathVariable final Long communityId) {
        communityService.deleteById(communityId);

        return ResponseEntity.noContent().build();
    }
}
