package com.est.helllow.service;

import com.est.helllow.domain.Post;
import com.est.helllow.domain.dto.PostRequestDto;
import com.est.helllow.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public Post save(PostRequestDto request){
        return postRepository.save(request.toEntity());
    }

    public List<Post> findAll(){
        return postRepository.findAll();
    }
}
