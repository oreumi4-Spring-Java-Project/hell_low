package com.est.helllow.controller;

import com.est.helllow.dto.ResponseDTO;
import com.est.helllow.dto.UserDTO;
import com.est.helllow.exception.BaseException;
import com.est.helllow.exception.BaseResponse;
import com.est.helllow.service.MyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MyInfoController {
    @Autowired
    MyInfoService myInfoService;

    @GetMapping("/myinfo/{userId}")
    public BaseResponse myinfo(@PathVariable String userId) {
        try {
            UserDTO myinfo = myInfoService.myinfo(userId);
            return new BaseResponse<>(myinfo);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getExceptionCode());
        }
    }

    @GetMapping("/mypostcount/{userId}")
    public BaseResponse mypostcount(@PathVariable String userId) {
        Map<String, Long> map = new HashMap<>();
        Long postCount = myInfoService.getPostCountByUserId(userId);
        Long replyCount = myInfoService.getReplyCountByUserId(userId);
        map.put("postCount", postCount);
        map.put("replyCount", replyCount);
        return new BaseResponse(map);
    }

//    @PutMapping("myinfo/{userId}")
//    public ResponseDTO updateMyInfo(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
//        userDTO.setUserId(userId);
//        UserDTO userDTO1 = myInfoService.updateUser(userDTO);
//        if(userDTO1 != null)
//            return new ResponseDTO("success", "update ok");
//        else
//            return new ResponseDTO("fail", "update fail");
//    }
}
