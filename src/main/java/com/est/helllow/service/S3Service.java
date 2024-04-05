package com.est.helllow.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class S3Service {

    @Autowired
    private AmazonS3 amazonS3;

    //private AmazonS3Client amazonS3Client;

    @Value("${aws.region}")
    private String region;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public String uploadImg(MultipartFile file) throws IOException {
        //파일이 없을 경우
        if (file == null) {
            return null;
        }
        String key = file.getOriginalFilename();

        amazonS3.putObject(makeRequest(file));

        return amazonS3.getUrl(bucketName, key).toString();
    }

    private PutObjectRequest makeRequest(MultipartFile file) throws IOException {
        String key = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        ObjectMetadata metadata = new ObjectMetadata();

        PutObjectRequest request = new PutObjectRequest(bucketName, key, inputStream, metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead);

        return request;
    }

    //이미지 삭제
    public void deleteImg(String imgUrl) {
        String key = getImgName(imgUrl);
        amazonS3.deleteObject(bucketName, key);
    }

    public String updateImg(MultipartFile newFile, String imgUrl) throws IOException {
        String originKey = getImgName(imgUrl);

        //사진만 삭제
        if (newFile == null) {
            amazonS3.deleteObject(bucketName, originKey);
            return null;
        }

        String newKey = newFile.getOriginalFilename();

        //사진 변경시
        if (newKey != originKey) {
            amazonS3.deleteObject(bucketName, originKey);
            amazonS3.putObject(makeRequest(newFile));
            return amazonS3.getUrl(bucketName, newKey).toString();
        }
        //사진 변경 안할 시
        return imgUrl;
    }


    private String getImgName(String imgUrl) {
        return imgUrl.split("/")[3];
    }

}
