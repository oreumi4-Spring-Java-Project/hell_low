package com.est.helllow.domain;

import com.est.helllow.domain.dto.PostResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {
    private Post post;

    @BeforeEach
    public void setUp() {
        post = new Post();
        post.setCategory("메뉴");
        post.setPostTitle("제목1");
        post.setPostContent("내용");
        post.setLikeCounts(0);
        post.setViewCounts(0);
        post.setPostFile("test_file.txt");

        User user = new User();
        user.setUserId("유저아이디1");
        post.setUser(user);
    }

    @Test
    public void testPostEntityCreate() {
        // given
        Post mockPost = Mockito.mock(Post.class);
        Mockito.when(mockPost.getCategory()).thenReturn("메뉴");
        Mockito.when(mockPost.getPostTitle()).thenReturn("제목1");
        Mockito.when(mockPost.getPostContent()).thenReturn("내용1");
        Mockito.when(mockPost.getLikeCounts()).thenReturn(0);
        Mockito.when(mockPost.getViewCounts()).thenReturn(0);
        Mockito.when(mockPost.getPostFile()).thenReturn("test_file.txt");
        LocalDateTime createdAt = LocalDateTime.now().minusDays(1);
        LocalDateTime modifiedAt = LocalDateTime.now();
        Mockito.when(mockPost.getCreatedAt()).thenReturn(createdAt);
        Mockito.when(mockPost.getModifiedAt()).thenReturn(modifiedAt);

        // when
        PostResponseDto postResponseDto = new PostResponseDto(mockPost);

        // then
        assertEquals("메뉴", postResponseDto.getCategory());
        assertEquals("제목1", postResponseDto.getPostTitle());
        assertEquals("내용1", postResponseDto.getPostContent());
        assertEquals(0, postResponseDto.getLikeCounts());
        assertEquals(0, postResponseDto.getViewCounts());
        assertEquals("test_file.txt", postResponseDto.getPostFile());
        assertEquals(createdAt, postResponseDto.getPostCreated());
        assertEquals(modifiedAt, postResponseDto.getPostModified());
    }

    @Test
    public void testUpdate() {
        //given -> 위 setup 메서드

        //when
        post.update("제목변경", "내용변경");

        //then
        assertEquals("제목변경", post.getPostTitle());
        assertEquals("내용변경", post.getPostContent());
    }

}