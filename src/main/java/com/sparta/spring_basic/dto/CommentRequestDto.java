package com.sparta.spring_basic.dto;

import com.sparta.spring_basic.entity.Writing;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {
    private String content;
    private Long writingID;
}
