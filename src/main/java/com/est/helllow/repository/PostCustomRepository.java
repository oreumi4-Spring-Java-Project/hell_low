package com.est.helllow.repository;

import com.est.helllow.domain.Post;
import com.est.helllow.domain.dto.PostSearchCondition;

import java.util.List;

public interface PostCustomRepository {
    public List<Post> search(PostSearchCondition postSearchCondition);
}
