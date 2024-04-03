package com.est.helllow.repository;


import com.est.helllow.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, String> {
    List<Reply> findByPost_PostId(String postId);

    List<Reply> findAllByUser_UserId(Long userId);

    Long countAllByUser_userId(Long userId);
}
