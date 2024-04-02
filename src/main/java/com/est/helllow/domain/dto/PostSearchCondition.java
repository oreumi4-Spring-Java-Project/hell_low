package com.est.helllow.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSearchCondition {
    private String searchText;

    @Builder
    public PostSearchCondition(String searchText) {//String title, String writer, String category
        this.searchText=searchText;
    }
}
