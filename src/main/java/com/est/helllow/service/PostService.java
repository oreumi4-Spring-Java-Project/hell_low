package com.est.helllow.service;

import com.est.helllow.domain.Post;
import com.est.helllow.domain.Reply;
import com.est.helllow.domain.dto.*;
import com.est.helllow.exception.BaseException;
import com.est.helllow.exception.BaseExceptionCode;
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

    public PostRes getPost(String postId) throws BaseException {
        Post findPost = postRepository.findById(postId).orElseThrow(()->new BaseException(BaseExceptionCode.NOT_EXIST_POST));
        findPost.setViewCounts(findPost.getViewCounts() + 1); // count + 1
        postRepository.save(findPost); // save
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

    public Post findById(String id) throws BaseException {
        return postRepository.findById(id)
                .orElseThrow(()-> new BaseException(BaseExceptionCode.NOT_EXIST_POST));
    }

    public void delete(String id) throws BaseException {
        // 게시물 존재 여부 체크
        postRepository.findById(id).orElseThrow(()->new BaseException(BaseExceptionCode.NOT_EXIST_POST));
        // 게시물 작성자 여부(이후 로그인 로직 반영 시 추가)

        postRepository.deleteById(id);
    }

    @Transactional
    public Post update(String id, PostRequestDto request) throws BaseException {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new BaseException(BaseExceptionCode.NOT_EXIST_POST));
        post.update(request.getPostTitle(), request.getPostContent());
        return post;
    }
}
