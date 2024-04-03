package com.est.helllow.service;

import com.est.helllow.domain.Post;
import com.est.helllow.domain.Reply;
import com.est.helllow.domain.dto.*;
import com.est.helllow.dto.PostDTO;
import com.est.helllow.repository.PostRepository;
import com.est.helllow.repository.ReplyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;


    // 게시물 검색 - CJW
    public List<Post> searchPost(PostSearchCondition postSearchCondition) {
        return postRepository.search(postSearchCondition);
    }

    //PostRes 형태로 모든 게시물 탐색 - CJW
    public PostRes getPost(String postId) {
        Post findPost = postRepository.findById(postId).orElseThrow(null);
        PostResponseDto responsePost = findPost.toResponse();

        List<ReplyResponseDto> replies = replyRepository.findByPost_PostId(postId).stream()
                .map(Reply::toResponse)
                .toList();
        return new PostRes(responsePost, replies);
    }

    //게시물 저장 - CJW
    public Post savePost(PostRequestDto request, String imgUrl) {
        return postRepository.save(request.toEntity(imgUrl));
    }

    //모든 게시물 탐색 - CJW
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    //특정 게시물 탐색 - CJW
    public Post findById(String id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found" + id + "post"));
    }

    //게시물 삭제 - CJW
    public void delete(String id) {
        postRepository.deleteById(id);
    }

    //게시물 수정 - CJW
    @Transactional
    public Post update(String id, PostRequestDto request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found" + id + "post"));
        post.update(request.getPostTitle(), request.getPostContent());
        return post;
    }

    //user가 작성한 post 개수 - KMG
    public Long getPostCountByUserId(Long userId) {
        Long postCount = postRepository.countAllByUser_userId(userId);
        return postCount;
    }

    //user가 작성한 post 정보 - KMG
    public List<Post> getMyPosts(Long userId) {
        return postRepository.findAllByUser_UserId(userId);
    }

}
