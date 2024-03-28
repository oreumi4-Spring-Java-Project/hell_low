package com.est.helllow.service;

import com.est.helllow.domain.Post;
import com.est.helllow.domain.dto.AddPostRequest;
import com.est.helllow.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public Post save(AddPostRequest request){
        return postRepository.save(request.toEntity());
    }
}
