package com.conatuseus.communityservice.domain.community.service;

import com.conatuseus.communityservice.domain.community.domain.Community;
import com.conatuseus.communityservice.domain.community.domain.CommunityRepository;
import com.conatuseus.communityservice.domain.community.service.dto.CommunityResponse;
import com.conatuseus.communityservice.domain.community.service.dto.CommunitySaveRequestDto;
import com.conatuseus.communityservice.domain.community.service.dto.CommunityUpdateRequestDto;
import com.conatuseus.communityservice.domain.community.service.exception.CommunityNotFoundByName;
import com.conatuseus.communityservice.domain.community.service.exception.CommunityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class CommunityService {

    private final CommunityRepository communityRepository;

    public CommunityResponse save(final CommunitySaveRequestDto requestDto) {
        Community community = communityRepository.save(requestDto.toEntity());

        return new CommunityResponse(community);
    }

    @Transactional(readOnly = true)
    public CommunityResponse findById(final Long communityId) {
        Community community = communityRepository.findById(communityId)
            .orElseThrow(() -> new CommunityNotFoundException(communityId));

        return new CommunityResponse(community);
    }

    public CommunityResponse update(final Long communityId, final CommunityUpdateRequestDto requestDto) {
        Community community = communityRepository.findById(communityId)
            .orElseThrow(() -> new CommunityNotFoundException(communityId));

        return new CommunityResponse(community.update(requestDto));
    }

    public void deleteById(final Long communityId) {
        Community community = communityRepository.findById(communityId)
            .orElseThrow(() -> new CommunityNotFoundException(communityId));

        communityRepository.delete(community);
    }

    @Transactional(readOnly = true)
    public List<Community> findAll() {
        return communityRepository.findAll();
    }

    public Community findByName(final String name) {
        return communityRepository.findByName(name)
            .orElseThrow(() -> new CommunityNotFoundByName(name));
    }
}
