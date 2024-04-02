package com.est.helllow.controller;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.est.helllow.domain.Post;
import com.est.helllow.domain.dto.PostRequestDto;
import com.est.helllow.domain.dto.PostRes;
import com.est.helllow.domain.dto.PostResponseDto;

import com.est.helllow.domain.dto.PostSearchCondition;
import com.est.helllow.service.PostService;
import com.est.helllow.service.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
public class PostController {
    PostService postService;
    S3Service s3Service;

    public PostController(PostService postService, S3Service s3Service) {
        this.postService = postService;
        this.s3Service = s3Service;
    }

    @PostMapping("api/posts")
    public ResponseEntity<PostResponseDto> addPost(@RequestBody PostRequestDto request) {
        Post newPost = postService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newPost.toResponse());
    }

    @PostMapping("api/upload/post")
    public ResponseEntity<PostResponseDto> uploadPost(@RequestPart(value = "postRequest") PostRequestDto request,
                                                      @RequestPart(value = "img", required = false) MultipartFile file){
        //파일이 있을 경우
        try{
            String imgUrl = s3Service.uploadImg(file);
            Post newPost = postService.savePost(request,imgUrl);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(newPost.toResponse());
        } catch(IOException e){
            return null;
        }
    }

    //form-data 각자 따로 입력
    @PostMapping("api/posts2")
    public String addPostImg(@RequestPart("file") MultipartFile file,
                             @RequestPart("title") String title,
                             @RequestPart("content") String content,
                             @RequestPart("category") String category) throws IOException {
        String bucketName = "hell-low";
        String key = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        ObjectMetadata metadata = new ObjectMetadata();

        s3Service.uploadFile(bucketName, key, inputStream, metadata);

        String preSignedUrl = s3Service.imageUrl(bucketName, key);

        postService.savePostWithImageUrlAndContent(title, content, category, preSignedUrl);

        return "Upload Successful!";
    }

    @PostMapping("api/posts3")
    public String addPost3(@RequestPart(value = "postRequest") PostRequestDto request,
                           @RequestPart(value = "boardImg", required = false) MultipartFile file) throws IOException {
        String bucketName = "hell-low";
        String key = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        ObjectMetadata metadata = new ObjectMetadata();

        s3Service.uploadFile(bucketName, key, inputStream, metadata);

        String preSignedUrl = s3Service.imageUrl(bucketName, key);

        postService.savePostWithImageUrlAndContent(request.getPostTitle(), request.getPostContent(), request.getCategory(), preSignedUrl);

        return "Success upload File!!";

    }

    @GetMapping("api/posts")
    public ResponseEntity<List<PostResponseDto>> findAllPosts() {
        List<PostResponseDto> postList = postService.findAll()
                .stream().map(PostResponseDto::new)
                .toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(postList);
    }

//    @GetMapping("api/posts/{postId}")
//    public ResponseEntity<PostResponseDto> findOnePost(@PathVariable Long postId){
//        PostResponseDto post = postService.findById(postId).toResponse();
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(post);
//    }

    /**
     * 게시물에 해당하는 댓글 리스트 조회 API
     *
     * @param postId
     * @return
     * @throws
     */
    @GetMapping("api/posts/{postId}")
    public ResponseEntity<PostRes> getPost(@PathVariable(name = "postId") Long postId) {
        PostRes postRes = postService.getPost(postId);
        return ResponseEntity.status(HttpStatus.OK).body(postRes);
    }

    /**
     * 게시물 검색 기능 API
     * 테스트 위한 구조 , 이후 변경 예정
     *
     * @return
     */
    @GetMapping("api/posts/search")
    public ResponseEntity<List<Post>> searchPost(@RequestBody PostSearchCondition postSearchCondition) {
        List<Post> posts = postService.searchPost(postSearchCondition);
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }


    @DeleteMapping("/api/posts/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.delete(postId);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/posts/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long postId, @RequestBody PostRequestDto request) {
        Post updatedPost = postService.update(postId, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updatedPost);
    }

}
