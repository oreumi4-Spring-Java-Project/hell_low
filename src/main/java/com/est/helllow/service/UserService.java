package com.est.helllow.service;

import com.est.helllow.domain.User;
import com.est.helllow.domain.dto.UserResponseDto;
import com.est.helllow.exception.BaseException;
import com.est.helllow.exception.BaseExceptionCode;
import com.est.helllow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
            /*if (userResponseDto.getUserName() != null) {
                user.setUserName(userResponseDto.getUserName()); // only name change
            }*/
            BeanUtils.copyProperties(userResponseDto, user, getNullPropertyNames(userResponseDto));
            user = userRepository.save(user);
            return UserResponseDto.toDTO(user);
        }
        return null;
    }

    // UserDTO에서 값이 있는 프로퍼티 이름 목록 반환
    private String[] getNullPropertyNames(UserResponseDto userDTO) {
        final BeanWrapper src = new BeanWrapperImpl(userDTO);
        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : src.getPropertyDescriptors()) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
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
