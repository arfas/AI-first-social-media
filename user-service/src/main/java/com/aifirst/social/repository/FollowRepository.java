package com.aifirst.social.repository;

import com.aifirst.social.model.Follow;
import com.aifirst.social.model.FollowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, FollowId> {
    List<Follow> findByFollowerId(Long followerId);
}
