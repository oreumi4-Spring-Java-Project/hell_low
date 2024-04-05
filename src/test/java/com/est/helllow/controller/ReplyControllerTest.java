package com.est.helllow.controller;


import com.est.helllow.domain.Post;
import com.est.helllow.domain.Reply;
import com.est.helllow.domain.User;
import com.est.helllow.domain.dto.ReplyRequestDto;

import com.est.helllow.exception.BaseException;
import com.est.helllow.exception.BaseExceptionCode;

import com.est.helllow.service.ReplyService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import static org.hamcrest.Matchers.is;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ReplyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReplyService replyService;

    @BeforeEach
    void setUp() throws BaseException {
        // 더미 User
        User mockUser = new User();

        mockUser.setUserId("유저1");
        mockUser.setUserName("닉네임");

        // 더미 Post
        Post mockPost = new Post("메뉴", "제목1", "내용", "파일 경로", mockUser);
        mockPost.setPostId("게시물id");

        // 더미 Reply
        Reply mockReply = new Reply();
        mockReply.setPost(mockPost);
        mockReply.setUser(mockUser);


        when(replyService.replySave(anyString(), anyString(), any(ReplyRequestDto.class))).thenReturn(mockReply);

        when(replyService.updateReply(anyString(), anyString(), anyString(), any(ReplyRequestDto.class))).thenReturn(mockReply);

        String deleteCommentId = "commentId";
        when(replyService.deleteComment(anyString(), anyString())).thenReturn(deleteCommentId);
    }


    @Test
    void replySaveSuccess() throws Exception {
        //given
        String postId = "게시물id";
        String userId = "유저1";

        //when
        ResultActions result = mockMvc.perform(post("/api.hell-low.com/comment-management/{postId}/comments/{userId}", postId, userId)
                        .param("content", "테스트 댓글"))
                .andExpect(status().isOk());

        //then
        result.andExpect(jsonPath("$.code", is(BaseExceptionCode.SUCCESS.getCode())))
                .andExpect(jsonPath("$.message", is(BaseExceptionCode.SUCCESS.getMessage())))
                .andExpect(jsonPath("$.result").exists());
    }

    @Test
    void updateReplySuccess() throws Exception {
        String postId = "게시물id";
        String userId = "유저1";
        String commentId = "commentId";

        ResultActions result = mockMvc.perform(put("/api.hell-low.com/comment-management/{postId}/comments/{userId}/{commentId}", postId, userId, commentId)
                        .param("content", "수정된 댓글"))
                .andExpect(status().isOk());

        result.andExpect(jsonPath("$.code", is(BaseExceptionCode.SUCCESS.getCode())))
                .andExpect(jsonPath("$.message", is(BaseExceptionCode.SUCCESS.getMessage())))
                .andExpect(jsonPath("$.result").exists());
    }


    @Test
    void deleteReplySuccess() throws Exception {
        //given
        String postId = "postId";
        String userId = "userId";
        String commentId = "commentId";

        //when
        ResultActions result = mockMvc.perform(delete("/api.hell-low.com/comment-management/{postId}/comments/{userId}/{commentId}", postId, userId, commentId))
                .andExpect(status().isOk());


        //then
        result.andExpect(jsonPath("$.code", is(BaseExceptionCode.SUCCESS.getCode())))
                .andExpect(jsonPath("$.message", is(BaseExceptionCode.SUCCESS.getMessage())))
                .andExpect(jsonPath("$.result", is(commentId + " 댓글이 삭제 되었습니다.")));
    }


}