package com.est.helllow.service;

import com.est.helllow.domain.Post;
import com.est.helllow.dto.PostDTO;
import com.est.helllow.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyworkService {
    @Autowired
    PostRepository postRepository;

    public List<PostDTO> getMyPosts (String userId) {
        List<Post> postList = postRepository.findByUser_userId(userId);
        List<PostDTO> postDTOList = postList.stream().map((post) -> PostDTO.toDTO(post)).collect(Collectors.toList());
        return postDTOList;
    }
}
