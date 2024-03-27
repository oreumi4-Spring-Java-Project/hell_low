package com.est.helllow.controller;

import com.est.helllow.domain.User;
import com.est.helllow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    public void UserSave(@RequestBody User user){
        userRepository.save(user);
    }
}
