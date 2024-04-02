package com.est.helllow.service;

import com.est.helllow.domain.User;
import com.est.helllow.dto.UserDTO;
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

    public UserDTO myinfo(Long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("not found"));
        return UserDTO.toDTO(user);
    }

    public Long getPostCountByUserId(Long userId) {
        Long postCount = postRepository.countAllByUser_userId(userId);
        return postCount;
    }
    public Long getReplyCountByUserId(Long userId) {
        Long myReplyCount = replyRepository.countAllByUser_userId(userId);
        return myReplyCount;
    }

    public UserDTO updateUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getUserId()).get();
        if(userDTO.getUserName() != null)
            user.setUserName(userDTO.getUserName());
        if(userDTO.getUserPw() != null)
            user.setUserPw(userDTO.getUserPw());
        user = userRepository.save(user);
        return UserDTO.toDTO(user);
    }
}
