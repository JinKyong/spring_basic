package com.sparta.spring_basic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WritingRequestDto {
    private String title;
    private String content;
    private String author;
    private String password;
}
