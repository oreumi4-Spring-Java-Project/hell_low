package com.est.helllow.domain;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc

class IdGeneratorTest {

    @Test
    void testPostIdGenerate(){
        // given
        Post post=new Post();
        post.setCategory("diets");


        //when
        post.prePersist();
        String postId = post.getPostId();
        System.out.println(postId);

        //then
        assertNotNull(post);
        assertTrue(postId.startsWith("post_diets"));
    }

    @Test
    void testComIdGenerate(){
        // given
        Post post = new Post();
        post.setCategory("diets");

        // when
        Reply reply = new Reply();
        reply.setPost(post);
        reply.prePersist();

        String commentId = reply.getComId();
        System.out.println(commentId);

        // then
        assertNotNull(reply);
        assertNotNull(commentId);
        assertTrue(commentId.startsWith("com_diets"));
    }

    @Test
    void testLikePostIdGenerate(){
        //given
        User user=new User();

        Post post =new Post();
        post.setCategory("diets");

        //when
        LikePost likePost=new LikePost(user,post);
        likePost.prePersist();

        String likeId = likePost.getLikeId();
        System.out.println(likeId);

        //then
        assertNotNull(likeId);
        assertTrue(likeId.startsWith("like_diets"));
    }

}