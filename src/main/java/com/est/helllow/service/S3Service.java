package com.est.helllow.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

@Service
public class S3Service {

    @Autowired
    private AmazonS3 amazonS3;

    //private AmazonS3Client amazonS3Client;

    @Value("${aws.region}")
    private String region;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public void uploadFile(String bucketName, String key, InputStream inputStream, ObjectMetadata metadata) {
        PutObjectRequest request = new PutObjectRequest(bucketName, key, inputStream, metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead);

        amazonS3.putObject(request);
    }

    public String uploadImg(MultipartFile file) throws IOException {
        //파일이 없을 경우
        if(file==null){
            return null;
        }
        //파일이름이 같을경우 오류 발생 가능
        String key = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        ObjectMetadata metadata = new ObjectMetadata();

        PutObjectRequest request = new PutObjectRequest(bucketName, key, inputStream, metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead);

        amazonS3.putObject(request);

        return amazonS3.getUrl(bucketName,key).toString();
        //return "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + key;
    }

    public String imageUrl(String bucketName, String key){
        return "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + key;
    }

/*
    public URL generatePresignedUrl(String bucketName, String key) {
        Date expiration = new Date(System.currentTimeMillis() + 3600000); // Set expiration time to 1 hour from now
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, key)
                .withMethod(HttpMethod.GET)
                .withExpiration(expiration);
        return amazonS3.generatePresignedUrl(request);
    }

 */


}
