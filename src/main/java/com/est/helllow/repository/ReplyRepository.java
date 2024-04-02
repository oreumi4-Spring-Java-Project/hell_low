package com.est.helllow.repository;

import com.est.helllow.domain.Post;
import com.est.helllow.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
      List<Reply> findByUser_userId(Long userId);
      List<Reply> findByPost_PostId(Long postId);

      Long countAllByUser_userId(Long userId);
}
