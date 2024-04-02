package com.est.helllow.service;

import com.est.helllow.domain.Post;
import com.est.helllow.domain.Reply;
import com.est.helllow.domain.dto.*;
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


    // 게시물 검색
    public List<Post> searchPost(PostSearchCondition postSearchCondition){
        return postRepository.search(postSearchCondition);
    }

    public PostRes getPost(Long postId){
        Post findPost = postRepository.findById(postId).orElseThrow(null);
        PostResponseDto responsePost = findPost.toResponse();

        List<ReplyResponseDto> replies = replyRepository.findByPost_PostId(postId).stream()
                .map(Reply::toResponse)
                .toList();
        return new PostRes(responsePost,replies);
    }

    //게시물 저장
    public Post savePost(PostRequestDto request, String imgUrl){
        return postRepository.save(request.toEntity(imgUrl));
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
