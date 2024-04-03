package com.est.helllow.controller;

import com.est.helllow.dto.ResponseDTO;
import com.est.helllow.dto.UserDTO;
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
    public UserDTO myinfo(@PathVariable String userId) {
        try {
            return myInfoService.myinfo(userId);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/mypostcount/{userId}")
    public Map<String, Long> mypostcount(@PathVariable String userId) {
        Map<String, Long> map = new HashMap<>();
        Long postCount = myInfoService.getPostCountByUserId(userId);
        Long replyCount = myInfoService.getReplyCountByUserId(userId);
        map.put("postCount", postCount);
        map.put("replyCount", replyCount);
        return map;
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
