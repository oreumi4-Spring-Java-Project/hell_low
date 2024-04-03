package com.est.helllow.service;

import com.est.helllow.domain.User;
import com.est.helllow.dto.UserDTO;
import com.est.helllow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDTO myinfo(Long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("not found"));
        return UserDTO.toDTO(user);
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
