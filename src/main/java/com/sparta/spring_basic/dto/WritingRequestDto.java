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
    //private String author;    //로그인해서 작성하기 때문에 불필요
    //private String password;
}
