package com.est.helllow.repository;

import com.est.helllow.domain.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikePostRepository extends JpaRepository<LikePost, String> {
    LikePost findLikePostByPost_PostIdAndUser_UserId(String postId, String userId);

    Long countAllByUser_userId(String userId);

    void deleteByPost_PostId(String postId);
    void deleteByUser_UserId(String userId);


}
