package com.est.helllow.controller;

import com.est.helllow.domain.Post;
import com.est.helllow.domain.User;
import com.est.helllow.exception.BaseExceptionCode;
import com.est.helllow.service.LikePostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class LikePostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LikePostService likePostService;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void likePostSuccess() throws Exception {
        // given
        String postId = "게시물 1";
        String userId = "유저아이디 1";
        int mockLikeCount = 1;
        when(likePostService.likePost(postId, userId)).thenReturn(mockLikeCount);

        // when
        ResultActions result = mockMvc.perform(post("/api.hell-low.com/like-management/{postId}/likes/{userId}", postId, userId))
                .andExpect(status().isOk());

        // then
        //json 값 일치여부 확인
        result.andExpect(jsonPath("$.code", is(BaseExceptionCode.SUCCESS.getCode())))
                .andExpect(jsonPath("$.message", is(BaseExceptionCode.SUCCESS.getMessage())))
                .andExpect(jsonPath("$.result", is(mockLikeCount)));
    }

    @Test
    void unLikePostSuccess() throws Exception {
        // given
        String postId = "게시물 1";
        String userId = "유저아이디 1";
        int mockLikeCount = 0;
        when(likePostService.unLikePost(postId, userId)).thenReturn(mockLikeCount);

        // when
        ResultActions result = mockMvc.perform(delete("/api.hell-low.com/like-management/{postId}/likes/{userId}", postId, userId))
                .andExpect(status().isOk());

        // then
        result.andExpect(jsonPath("$.code", is(BaseExceptionCode.SUCCESS.getCode())))
                .andExpect(jsonPath("$.message", is(BaseExceptionCode.SUCCESS.getMessage())))
                .andExpect(jsonPath("$.result", is(mockLikeCount)));
    }
}
