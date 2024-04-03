package com.est.helllow.exception;

import com.est.helllow.exception.BaseExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseException extends Exception{
    private BaseExceptionCode exceptionCode;
}
