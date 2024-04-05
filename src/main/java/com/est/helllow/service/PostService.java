package com.est.helllow.service;

import com.est.helllow.domain.Post;
import com.est.helllow.domain.Reply;
import com.est.helllow.domain.User;
import com.est.helllow.domain.dto.*;
import com.est.helllow.exception.BaseException;
import com.est.helllow.exception.BaseExceptionCode;
import com.est.helllow.repository.PostRepository;
import com.est.helllow.repository.ReplyRepository;
import com.est.helllow.repository.UserRepository;
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
    private final UserRepository userRepository;


    // 게시물 검색 - CJW
    public List<PostResponseDto> searchPost(PostSearchCondition postSearchCondition) {
        return postRepository.search(postSearchCondition)
                .stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    //PostRes 형태로 모든 게시물 탐색 - CJW
    public PostRes getPost(String postId) throws BaseException {
        Post findPost = postRepository.findById(postId).orElseThrow(() -> new BaseException(BaseExceptionCode.NOT_EXIST_POST));
        findPost.setViewCounts(findPost.getViewCounts() + 1); // count + 1
        postRepository.save(findPost); // save
        PostResponseDto responsePost = findPost.toResponse();

        List<ReplyResponseDto> replies = replyRepository.findByPost_PostId(postId).stream()
                .map(Reply::toResponse)
                .toList();
        return new PostRes(responsePost, replies);
    }

    //게시물 저장 - CJW
    public Post savePost(String userId, PostRequestDto request, String imgUrl) throws BaseException {
        User user = userRepository.findById(userId).orElseThrow(() -> new BaseException(BaseExceptionCode.NOT_INVALID_USER));
        return postRepository.save(request.toEntity(user, imgUrl));
    }

    //모든 게시물 탐색 - CJW
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    //특정 postId의 게시물 탐색 - CJW
    public Post findById(String id) throws BaseException {
        return postRepository.findById(id)
                .orElseThrow(() -> new BaseException(BaseExceptionCode.NOT_EXIST_POST));
    }

    //게시물 삭제 - CJW
    public void delete(String postId,String userId) throws BaseException {
        Post findPost = postRepository.findById(postId).orElseThrow(() -> new BaseException(BaseExceptionCode.NOT_EXIST_POST));

        // 게시물 존재 여부 체크
        if(findPost.getPostId()==null){
            throw new BaseException(BaseExceptionCode.NOT_EXIST_POST);
        }

        // 게시물 작성자 여부(이후 로그인 로직 반영 시 추가)
        if(!findPost.getUser().getUserId().equals(userId)){
            throw new BaseException(BaseExceptionCode.NOT_INVALID_USER);
        }



        postRepository.deleteById(postId);
    }

    //게시물 수정 - CJW
    @Transactional
    public Post update(String postId, PostRequestDto request, String imgUrl,String userId) throws BaseException {
        Post post = postRepository.findById(postId).orElseThrow(() -> new BaseException(BaseExceptionCode.NOT_EXIST_POST));

        // 게시물 존재 여부 체크
        if(post.getPostId()==null){
            throw new BaseException(BaseExceptionCode.NOT_EXIST_POST);
        }

        // 게시물 작성자 여부(이후 로그인 로직 반영 시 추가)
        if(!post.getUser().getUserId().equals(userId)){
            throw new BaseException(BaseExceptionCode.NOT_INVALID_USER);
        }

        post.update(request, imgUrl);
        return post;
    }

    //게시물 개수 - CJW
    public Long getPostCount() throws BaseException {
        Long postCount = postRepository.count();
        return postCount;
    }

    //user가 작성한 post 개수 - KMG
    public Long getPostCountByUserId(String userId) {
        Long postCount = postRepository.countAllByUser_userId(userId);
        return postCount;
    }

    //user가 작성한 post 정보 - KMG
    public List<Post> getMyPosts(String userId) {
        return postRepository.findAllByUser_UserId(userId);
    }

    //user가 작성한 post 삭제
    public void deletePostByUserId(String userId){
        postRepository.deleteByUser_userId(userId);
    }
}
