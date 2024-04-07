package com.est.helllow.controller;

import com.est.helllow.dto.UserDTO;
import com.est.helllow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * @param id
     * @return UserDTO
     * @author kmg
     * user 정보를 반환하는 API
     */
    @GetMapping("api.hell-low.com/user-management/users/{id}")
    public UserDTO myinfo(@PathVariable Long id) {
        try {
            return userService.myinfo(id);
        } catch (Exception e) {
            return null;
        }
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
