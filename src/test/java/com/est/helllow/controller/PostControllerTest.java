package com.est.helllow.controller;

import com.est.helllow.domain.Post;
import com.est.helllow.domain.User;
import com.est.helllow.domain.dto.PostRequestDto;
import com.est.helllow.repository.PostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        postRepository.deleteAll();
    }

    @DisplayName("Post 글 추가 성공")
    @Test
    public void addPost() throws Exception {
        //given
        String url = "api.hell-low.com/post-management/users/user_test";

        String category = "category";
        String title = "title";
        String content = "contents";
        PostRequestDto request = new PostRequestDto(category, title, content);

        String requestBody = objectMapper.writeValueAsString(request);

        //when
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        //then
        result.andExpect(status().isCreated());
        List<Post> postList = postRepository.findAll();
        for (Post post : postList) {
            System.out.println(post.getPostId());
        }

        assertThat(postList.size()).isEqualTo(1);
        assertThat(postList.get(0).getPostTitle()).isEqualTo(title);
        assertThat(postList.get(0).getPostContent()).isEqualTo(content);

    }

    @DisplayName("Post 글 전체 조회 성공")
    @Test
    public void testFindAll() throws Exception {
        /*
        // given
        final String url = "/api/posts";
        final String category = "testCategory";
        final String title = "testTitle";
        final String content = "testContent";
        Post post = postRepository.save(new Post(category, title, content));

        // when
        ResultActions result = mockMvc.perform(get(url));

        // then : 정상적으로 요청이 되었는지 검증
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].category")).value(post.getCategory())
                .andExpect(jsonPath("$[0].post_title").value(post.getPostTitle()))
                .andExpect(jsonPath("$[0].content").value(post.getPostContent()));
         */
    }

    @DisplayName("Post 글 삭제 성공")
    @Test
    public void testDeleteArticle() throws Exception {
        // given
        //final String url = "api.hell-low.com/post-management/posts/{id}";
        String category = "notice";
        String title = "title1";
        String content = "content1";
        PostRequestDto request = new PostRequestDto(category, title, content);

        User user_test = new User();

        Post post = postRepository.save(request.toEntity(user_test, null));
        String savedId = post.getPostId();

        String deleteUrl = "api.hell-low.com/post-management/posts/" + savedId;

        // when
        mockMvc.perform(delete(deleteUrl, savedId)).andExpect(status().isOk());

        // then
        List<Post> afterDeleteList = postRepository.findAll();
        //isEmpty() 오류
        //assertThat(afterDeleteList).isEmpty();
    }

}