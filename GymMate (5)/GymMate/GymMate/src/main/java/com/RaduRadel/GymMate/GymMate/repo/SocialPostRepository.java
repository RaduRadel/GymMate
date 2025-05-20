package com.RaduRadel.GymMate.GymMate.repo;

import com.RaduRadel.GymMate.GymMate.model.SocialPost;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SocialPostRepository extends JpaRepository<SocialPost, Long> {
    List<SocialPost> findAllByOrderByCreatedAtDesc();
}
