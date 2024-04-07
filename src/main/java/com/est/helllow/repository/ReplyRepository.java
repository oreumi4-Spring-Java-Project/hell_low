package com.est.helllow.repository;


import com.est.helllow.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, String> {
      List<Reply> findAllByUser_UserId(String userId);
      List<Reply> findByPost_PostId(String postId);
      Long countAllByUser_userId(String userId);
      void deleteByPost_PostId(String postId);
      void deleteByUser_UserId(String userId);
}
