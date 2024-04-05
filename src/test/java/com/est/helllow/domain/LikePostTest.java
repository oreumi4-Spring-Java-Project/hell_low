package com.est.helllow.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class LikePostTest {
    @Mock
    private User mockUser;

    @Mock
    private Post mockPost;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLikePostCreation() {
        // give
        LikePost likePost = new LikePost(mockUser, mockPost);


        // then
        assertNotNull(likePost);
        assertEquals(mockUser, likePost.getUser());
        assertEquals(mockPost, likePost.getPost());
    }

    @Test
    public void testPrePersist() {
        // give
        LikePost likePost = new LikePost(mockUser, mockPost);

        // when
        likePost.prePersist();

        // then
        assertNotNull(likePost.getLikeId());
    }
}

