package com.est.helllow.controller;

import com.est.helllow.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepository;


}
