package com.est.helllow.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BaseExceptionCode {

    /**
     * 200 : 요청 성공
     */
    SUCCESS(HttpStatus.OK.value(),"요청에 성공하였습니다"),

    /**
     * 4xx : 요청,응답 오류
     */
    //[LOGIN]

    //[USER]

    //[POST]
    NOT_EXIST_POST(HttpStatus.NOT_FOUND.value(), "존재하지 않는 게시물입니다"),
    //[REPLY]
    NOT_EXIST_REPLY(HttpStatus.NOT_FOUND.value(), "존재하지 않는 댓글입니다."),
    //[LIKE]
    FAILED_TO_LIKE_POST(HttpStatus.BAD_REQUEST.value(), "게시물 좋아요를 실패했습니다"),
    FAILED_TO_UNLIKE_POST(HttpStatus.BAD_REQUEST.value(), "게시물 좋아요 해제를 실패했습니다");
    private final int code;
    private final String message;
}
