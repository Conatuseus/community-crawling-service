package com.conatuseus.communityservice.domain.community.service;

import com.conatuseus.communityservice.domain.community.domain.Community;
import com.conatuseus.communityservice.domain.community.domain.CommunityRepository;
import com.conatuseus.communityservice.domain.community.service.dto.CommunityResponse;
import com.conatuseus.communityservice.domain.community.service.dto.CommunitySaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class CommunityService {

    private final CommunityRepository communityRepository;

    public CommunityResponse save(final CommunitySaveRequestDto requestDto) {
        Community community = communityRepository.save(requestDto.toEntity());

        return new CommunityResponse(community);
    }
}
