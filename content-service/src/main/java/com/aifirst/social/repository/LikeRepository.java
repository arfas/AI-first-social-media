package com.aifirst.social.repository;

import com.aifirst.social.model.Like;
import com.aifirst.social.model.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, LikeId> {
}
