package com.est.helllow.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

@Service
public class S3Service {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.region}")
    private String region;

    public void uploadFile(String bucketName, String key, InputStream inputStream, ObjectMetadata metadata) {
        PutObjectRequest request = new PutObjectRequest(bucketName, key, inputStream, metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead);

        amazonS3.putObject(request);
    }

    // Implement other methods for download, delete, etc.
    public URL generatePresignedUrl(String bucketName, String key) {
        Date expiration = new Date(System.currentTimeMillis() + 3600000); // Set expiration time to 1 hour from now
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, key)
                .withMethod(HttpMethod.GET)
                .withExpiration(expiration);
        return amazonS3.generatePresignedUrl(request);
    }

    public String imageUrl(String bucketName, String key){
        return "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + key;
    }
}
