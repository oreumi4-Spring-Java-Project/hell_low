package com.est.helllow.controller;

import com.est.helllow.domain.Post;
import com.est.helllow.domain.RegistrationSource;
import com.est.helllow.domain.User;
import com.est.helllow.domain.dto.PostRequestDto;
import com.est.helllow.domain.dto.UserResponseDto;
import com.est.helllow.domain.enum_class.UserGrade;
import com.est.helllow.repository.PostRepository;
import com.est.helllow.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.AfterEach;
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

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static com.est.helllow.domain.enum_class.UserGrade.*;
import static com.est.helllow.domain.enum_class.UserRole.ROLE_USER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserController userController;

    @Autowired
    UserRepository userRepository;
    @Autowired
    ObjectMapper objectMapper; // <--

    @BeforeEach
    public void mockMvcSetUp() {
        // userId를 셋팅 하지 말자
        User user = new User();
        user.setUserName("test");
        user.setUserEmail("test1@test.com");
        user.setUserGrade(SBD_300);
        user.setUserImg("1");
        user.setRole(ROLE_USER);
        user.setSource(RegistrationSource.KaKao);
        user.setCreatedAt(LocalDateTime.now());
        user.setModifiedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    @Test
    public void userCount() {
        List<User> userList = userRepository.findAll();
        assert userList.size() == 3;
    }

    @DisplayName("get user by userid")
    @Test
    public void getUser() throws Exception {
        //given
        // String url = "/api.hell-low.com/user-management/users/user_8d3ed619-792b-418f-8657-5a42474247b3";
        // email로 userId를 가져와서 해당 userId로 url을 만들어서 요청한다.
        User user = userRepository.findByUserEmail("test1@test.com").get();
        String url = "/api.hell-low.com/user-management/users/" + user.getUserId();

        //when
        ResultActions result = mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8));

        //then
        result.andExpect(status().isOk())
                .andExpect(content().string(containsString("test")));

    }

    @Test
    public void modifyUser() throws Exception {
        // given
        // 우선 해당 email의 User의 전체 값을 가져오고 그중 email만 바꿔봄
        User user = userRepository.findByUserEmail("test1@test.com").get();
        String url = "/api.hell-low.com/user-management/users/" + user.getUserId();
        UserResponseDto requestDto = UserResponseDto.toDTO(user);
        requestDto.setUserName("modify");
        // ObjectMapper objectMapper = new ObjectMapper(); // 'java object' to 'json' format 변환
        String requestBody = objectMapper.writeValueAsString(requestDto);

        // when
        // 위의 파라미터를 이용해서 실제 요청을 날려서 데이터를 수정한다.
        ResultActions result = mockMvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(requestBody)
        );

        // then
        // email이 제대로 바꼈는지 검증
        result.andExpect(status().isOk());

        // 위에서 수정한 userId의 email을 확인
        User user1 = userRepository.findById(user.getUserId()).get();
        assertThat(user1.getUserName()).isEqualTo("modify");
    }

//    @Test
//    public void deleteUser() throws Exception {
//        // given
//        User user = new User();
//        user.setUserName("test2");
//        user.setUserEmail("test2@test.com");
//        user.setUserGrade(SBD_300);
//        user.setUserImg("1");
//        user.setRole(ROLE_USER);
//        user.setSource(RegistrationSource.KaKao);
//        user.setCreatedAt(LocalDateTime.now());
//        user.setModifiedAt(LocalDateTime.now());
//        userRepository.save(user);
//
//        User user2 = userRepository.findByUserEmail("test2@test.com").get();
//        String url = "/api.hell-low.com/user-management/users/" + user2.getUserId();
//
//        // when
//        // 위의 파라미터를 이용해서 실제 요청을 날려서 데이터를 삭제한다.
//        ResultActions result = mockMvc.perform(delete(url)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON_UTF8)
//        );
//
//        // then
//        // 삭제여부 체크
//        result.andExpect(status().isOk());
//
//        // 위에서 수정한 userId의 email을 확인
//        User user3 = userRepository.findById(user2.getUserId()).orElse(null);
//        assert user3 == null;
//    }

    // 이렇게 하면 테스트 수행후 넣어둔 값이 삭제 되어 테스트가 계속 성공
    @AfterEach
    public void clean() {
        User user = userRepository.findByUserEmail("test1@test.com").get();
        userRepository.deleteById(user.getUserId());
    }

}