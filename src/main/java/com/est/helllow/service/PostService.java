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

    public PostRes getPost(String postId){
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

    public void savePostWithImageUrlAndContent(String title, String content, String category,String imageUrl) {
        Post post = new Post();
        post.setPostTitle(title);
        post.setPostContent(content);
        post.setCategory(category);
        post.setPostFile(imageUrl);

        postRepository.save(post);
    }

    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public Post findById(String id){
        return postRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found" + id + "post"));
    }

    public void delete(String id){
        postRepository.deleteById(id);
    }

    @Transactional
    public Post update(String id, PostRequestDto request){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found" + id + "post"));
        post.update(request.getPostTitle(), request.getPostContent());
        return post;
    }
}
