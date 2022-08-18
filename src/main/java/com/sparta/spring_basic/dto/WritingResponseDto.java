package com.sparta.spring_basic.dto;

import com.sparta.spring_basic.entity.Writing;
import lombok.Getter;

@Getter
public class WritingResponseDto {
    private final Writing response;

    public WritingResponseDto(Writing writing){
        response = writing;
    }
}
