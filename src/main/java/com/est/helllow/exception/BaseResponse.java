package com.est.helllow.exception;

import com.est.helllow.exception.BaseExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.est.helllow.exception.BaseExceptionCode.SUCCESS;

@Getter
@AllArgsConstructor
public class BaseResponse<T> {
    private final int code;
    private final String message;
    private T result;

    // 요청에 성공
    public BaseResponse(T result){
        this.code= SUCCESS.getCode();
        this.message=SUCCESS.getMessage();
        this.result=result;
    }

    //요청에  실패
    public BaseResponse(BaseExceptionCode exceptionCode){
        this.code=exceptionCode.getCode();
        this.message=exceptionCode.getMessage();
    }
}
