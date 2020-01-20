package com.conatuseus.communityservice.domain.community.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommunityRepository extends JpaRepository<Community, Long> {

    Optional<Community> findByNameAndKeyword(final String name, final String keyword);
}
