package com.est.helllow.service;

import com.est.helllow.domain.Post;
import com.est.helllow.domain.Reply;
import com.est.helllow.domain.dto.PostRequestDto;
import com.est.helllow.domain.dto.PostRes;
import com.est.helllow.domain.dto.PostResponseDto;
import com.est.helllow.domain.dto.ReplyResponseDto;
import com.est.helllow.repository.PostRepository;
import com.est.helllow.repository.ReplyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;


    public PostRes getPost(Long postId){
        Post findPost = postRepository.findById(postId).orElseThrow(null);
        PostResponseDto responsePost = findPost.toResponse();

        List<ReplyResponseDto> replies = replyRepository.findByPost_PostId(postId).stream()
                .map(Reply::toResponse)
                .toList();
        return new PostRes(responsePost,replies);
    }


//    public PostService(PostRepository postRepository){
//        this.postRepository = postRepository;
//    }

    public Post save(PostRequestDto request){
        return postRepository.save(request.toEntity());
    }

    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public Post findById(Long id){
        return postRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found" + id + "post"));
    }

    public void delete(long id){
        postRepository.deleteById(id);
    }

    @Transactional
    public Post update(Long id, PostRequestDto request){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found" + id + "post"));
        post.update(request.getPostTitle(), request.getPostContent());
        return post;
    }
}
