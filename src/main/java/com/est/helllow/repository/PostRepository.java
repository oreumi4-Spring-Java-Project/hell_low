package com.est.helllow.repository;

import com.est.helllow.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser_userId(Long userId);

    Long countAllByUser_userId(Long userId);
}
