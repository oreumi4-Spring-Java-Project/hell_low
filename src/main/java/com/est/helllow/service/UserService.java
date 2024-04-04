package com.est.helllow.service;

import com.est.helllow.domain.User;
import com.est.helllow.domain.dto.UserResponseDto;
import com.est.helllow.exception.BaseException;
import com.est.helllow.exception.BaseExceptionCode;
import com.est.helllow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto myinfo(String userId) throws BaseException {
        User user = userRepository.findById(userId).orElseThrow(() -> new BaseException(BaseExceptionCode.NOT_INVALID_USER));
        return UserResponseDto.toDTO(user);
    }

    //작성자 - 김민규, 유저 정보 수정 코드
    public UserResponseDto updateUser(UserResponseDto userResponseDto) {
        User user = userRepository.findById(userResponseDto.getUserId()).orElse(null);
        if (user != null) {
            if (userResponseDto.getUserName() != null) {
                user.setUserName(userResponseDto.getUserName());
            }
            user = userRepository.save(user);
            return UserResponseDto.toDTO(user);
        }
        return null;
    }

    //작성자 - 김민규, 유저 정보 삭제 코드
    public boolean deleteUser(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return true;
        } else {
            return false; // 사용자를 찾을 수 없는 경우, false 반환
        }
    }
}
