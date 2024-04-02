package com.est.helllow.controller;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.est.helllow.domain.Post;
import com.est.helllow.domain.dto.PostRequestDto;
import com.est.helllow.service.PostService;
import com.est.helllow.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@RestController
public class S3Controller {
    @Autowired
    private S3Service s3Service;

    @Autowired
    private PostService postService;

//    @PostMapping("/upload")
//    public String uploadImage(@ModelAttribute PostRequestDto postRequestDto) throws IOException {
//        String bucketName = "hell-low";
//        String key = postRequestDto.getImageFile().getOriginalFilename();
//        // You can handle the file content in postRequestDto.getImageFile().getInputStream() if needed
//        MultipartFile imageFile = postRequestDto.getImageFile();
//        s3Service.uploadFile(bucketName, key, imageFile.getInputStream(), null);
//
//        // Get the URL of the uploaded image from S3
//        URL preSignedUrl = s3Service.generatePresignedUrl(bucketName, key);
//        System.out.println(preSignedUrl.toString());
//        // Save the post entity with the title, content, and image URL
//        postService.savePostWithImageUrlAndContent(postRequestDto.getPostTitle(), postRequestDto.getPostContent(), "notice", preSignedUrl.toString());
//
//        return "Upload successful!";
//    }

    @PostMapping("/posts/imgupload")
    public String addPostImg(@RequestPart("file") MultipartFile file,
                                           @RequestPart("title") String title,
                                           @RequestPart("content") String content,
                                            @RequestPart("category") String category) throws IOException {
        String bucketName = "hell-low";
        String key = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        ObjectMetadata metadata = new ObjectMetadata();

        s3Service.uploadFile(bucketName, key, inputStream, metadata);

        String preSignedUrl = s3Service.imageUrl(bucketName,key);

        postService.savePostWithImageUrlAndContent(title,content,category,preSignedUrl);

        return "Upload Successful!";
    }
}
