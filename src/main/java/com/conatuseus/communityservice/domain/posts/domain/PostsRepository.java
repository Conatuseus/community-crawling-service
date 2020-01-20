package com.conatuseus.communityservice.domain.posts.domain;

import com.conatuseus.communityservice.domain.community.domain.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    boolean existsByLink(final String link);

    List<Posts> findByCommunityAndKeywordOrderByModifiedDate(final Community community, final String keyword);
}
