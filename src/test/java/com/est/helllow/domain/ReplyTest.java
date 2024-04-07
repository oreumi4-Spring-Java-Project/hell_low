package com.est.helllow.domain;

import com.est.helllow.domain.dto.ReplyResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class ReplyTest {
    @Mock
    private Post mockPost;

    @Mock
    private User mockUser;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this); // 목 객체 초기화 용
    }

    @Test
    void testConvertResponse(){
        //given
        String content="댓글1";
        String comId="댓글아디1";
        Reply reply = Reply.builder()
                .post(mockPost)
                .user(mockUser)
                .content(content)
                .comId(comId)
                .build();

        //when
        ReplyResponseDto response = reply.toResponse();

        //then
        assertNotNull(response);
        assertEquals(mockPost.getPostId(), response.getPostId());
        assertEquals(mockUser.getUserName(), response.getNickname());
    }

    @Test
    void testUpdateReply() {
        // given
        String initialContent = "댓글1";
        String updatedContent = "댓글 2";
        Reply reply = Reply.builder()
                .post(mockPost)
                .user(mockUser)
                .content(initialContent)
                .build();

        // when
        reply.updateReply(updatedContent);

        // then
        assertEquals(updatedContent, reply.getContent());
    }
}