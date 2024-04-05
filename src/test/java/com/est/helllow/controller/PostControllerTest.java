package com.est.helllow.controller;

import com.est.helllow.domain.Post;
import com.est.helllow.domain.dto.PostRequestDto;
import com.est.helllow.service.PostService;
import com.est.helllow.service.S3Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;
import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class PostControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;
    @MockBean
    private S3Service s3Service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addPostSuccess() throws Exception {
        // given
        String userId = "유저아이디1";
        PostRequestDto requestDto = new PostRequestDto("카테고리1", "제목1", "내용1");
        String requestDtoJson = asJsonString(requestDto);

        MockMultipartFile postRequestPart =
                new MockMultipartFile("postRequest", "", "application/json", requestDtoJson.getBytes(StandardCharsets.UTF_8));
        MockMultipartFile filePart =
                new MockMultipartFile("img", "filename.txt", "text/plain", "내용1".getBytes());

        Post post = new Post();
        when(s3Service.uploadImg(any(MultipartFile.class))).thenReturn("image-url");
        when(postService.savePost(anyString(), any(PostRequestDto.class), anyString())).thenReturn(post);

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.multipart("/api.hell-low.com/post-management/{userId}", userId)
                        .file(postRequestPart)
                        .file(filePart)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk());
    }

    @Test
    void updatePostSuccess() throws Exception {
        // given
        String userId = "유저아이디1";
        String postId = "포스트ID1";
        PostRequestDto requestDto = new PostRequestDto("카테고리1", "제목1", "내용1");
        String requestDtoJson = asJsonString(requestDto);

        MockMultipartFile postRequestPart =
                new MockMultipartFile("postRequest", "", "application/json", requestDtoJson.getBytes(StandardCharsets.UTF_8));
        MockMultipartFile filePart =
                new MockMultipartFile("img", "filename.txt", "text/plain", "이미지 내용".getBytes());

        // Mock 서비스 동작 지정
        when(postService.findById(anyString())).thenReturn(new Post());
        when(postService.update(anyString(), any(PostRequestDto.class), anyString(), anyString())).thenReturn(new Post());
        when(s3Service.uploadImg(any(MultipartFile.class))).thenReturn("image-url");
        when(s3Service.updateImg(any(MultipartFile.class), anyString())).thenReturn("updated-image-url");

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.multipart("/api.hell-low.com/post-management/{userId}/{postId}", userId, postId)
                        .file(postRequestPart)
                        .file(filePart)
                        .with(request -> { request.setMethod("PUT"); return request; })
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk());
    }


    //객체 -> json문자열 변환 메서드
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
