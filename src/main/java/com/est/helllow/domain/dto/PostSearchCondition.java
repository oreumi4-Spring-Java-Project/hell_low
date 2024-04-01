package com.est.helllow.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSearchCondition {
//    private String searchText;
    private String title;
    private String writer;
    private String category;

    @Builder
    public PostSearchCondition(String title, String writer, String category) {
//        this.searchText=searchText;
        this.title = title;
        this.writer = writer;
        this.category = category;
    }
}
