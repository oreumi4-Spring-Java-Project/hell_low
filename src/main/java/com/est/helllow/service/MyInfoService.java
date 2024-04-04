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

//    public UserDTO updateUser(UserDTO userDTO) {
//        User user = userRepository.findById(userDTO.getUserId()).get();
//        if(userDTO.getUserName() != null)
//            user.setUserName(userDTO.getUserName());
//        if(userDTO.getUserPw() != null)
//            user.setUserPw(userDTO.getUserPw());
//        user = userRepository.save(user);
//        return UserDTO.toDTO(user);
//    }
}
