package com.RaduRadel.GymMate.GymMate.repo;

import com.RaduRadel.GymMate.GymMate.model.SocialLike;
import com.RaduRadel.GymMate.GymMate.model.SocialPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialLikeRepository extends JpaRepository<SocialLike, Long> {
    boolean existsByPostAndUsername(SocialPost post, String username);

    void deleteAllByPost(SocialPost post);
}
