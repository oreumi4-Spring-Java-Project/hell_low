package com.est.helllow.repository;

import com.est.helllow.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, String>,PostCustomRepository{
    List<Post> findByUser_userId(String userId);

    Long countAllByUser_userId(String userId);

}
