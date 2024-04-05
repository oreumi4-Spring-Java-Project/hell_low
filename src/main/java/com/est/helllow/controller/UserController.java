package com.est.helllow.controller;

import com.est.helllow.dto.ResponseDTO;
import com.est.helllow.domain.dto.UserResponseDto;
import com.est.helllow.exception.BaseException;
import com.est.helllow.exception.BaseResponse;
import com.est.helllow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("api.hell-low.com/user-management/users/{userId}")
    public BaseResponse myinfo(@PathVariable String userId) {
        try {
            UserResponseDto myinfo = userService.myinfo(userId);
            return new BaseResponse<>(myinfo);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getExceptionCode());
        }
    }

    @PutMapping("api.hell-low.com/user-management/users/{userId}")
    public ResponseDTO updateMyInfo(@PathVariable String userId, @RequestBody UserResponseDto userResponseDto) {
        userResponseDto.setUserId(userId);
        UserResponseDto userResponseDto1 = userService.updateUser(userResponseDto);
        if (userResponseDto1 != null)
            return new ResponseDTO("success", "update ok");
        else
            return new ResponseDTO("fail", "update fail");
    }

    @DeleteMapping("api.hell-low.com/user-management/users/{userId}")
    public ResponseDTO deleteMyInfo(@PathVariable String userId) {
        boolean result = userService.deleteUser(userId);
        if (result) {
            return new ResponseDTO("success", "delete ok");
        } else {
            return new ResponseDTO("fail", "delete fail");
        }
    }
}
