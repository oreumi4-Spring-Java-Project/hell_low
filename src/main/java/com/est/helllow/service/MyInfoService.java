package com.est.helllow.service;

import com.est.helllow.domain.User;
import com.est.helllow.dto.UserDTO;
import com.est.helllow.exception.BaseException;
import com.est.helllow.exception.BaseExceptionCode;
import com.est.helllow.repository.PostRepository;
import com.est.helllow.repository.ReplyRepository;
import com.est.helllow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyInfoService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    ReplyRepository replyRepository;

    public UserDTO myinfo(String userId) throws BaseException {
        User user = userRepository.findById(userId).orElseThrow(()->new BaseException(BaseExceptionCode.NOT_INVALID_USER));
        return UserDTO.toDTO(user);
    }

    public Long getPostCountByUserId(String userId) {
        Long postCount = postRepository.countAllByUser_userId(userId);
        return postCount;
    }
    public Long getReplyCountByUserId(String userId) {
        Long myReplyCount = replyRepository.countAllByUser_userId(userId);
        return myReplyCount;
    }


    //작성자 - 김민규, 유저 정보 수정 코드
    public UserDTO updateUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getUserId()).orElse(null);
        if (user != null) {
            if(userDTO.getUserName() != null) {
                user.setUserName(userDTO.getUserName());
            }
            user = userRepository.save(user);
            return UserDTO.toDTO(user);
        }
        return null;
    }

    //작성자 - 김민규, 유저 정보 삭제 코드
    public boolean deleteUser(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            userRepository.delete(user.get());
            return true;
        } else {
            return false; // 사용자를 찾을 수 없는 경우, false 반환
        }
    }
}
