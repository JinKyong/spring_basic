package com.sparta.spring_basic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDto {
    private String nickname;
    private String password;
    private String passwordConfirm;
}
