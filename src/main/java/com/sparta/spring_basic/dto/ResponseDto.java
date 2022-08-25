package com.sparta.spring_basic.dto;

import com.sparta.spring_basic.ExceptionHandler.CustomException;
import com.sparta.spring_basic.ExceptionHandler.ErrorCode;
import com.sparta.spring_basic.entity.Writing;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto<T> {
    private boolean success;
    private T data;
    private ErrorCode error;

    public ResponseDto(T data){
        this.data = data;
    }

    public static <T> ResponseDto<T> success(T data) {
        return new ResponseDto<>(true, data, null);
    }

    public static <T> ResponseDto<T> fail(ErrorCode code) { return new ResponseDto<>(false, null, code); }
}
