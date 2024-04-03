package com.est.helllow.service;

import java.util.Optional;


import org.springframework.context.annotation.ComponentScan;

import com.est.helllow.domain.User;

@ComponentScan
public interface UserInterfaceService {

    Optional<User> findByuserEmail(String userEmail);

    void save(User user);
}

