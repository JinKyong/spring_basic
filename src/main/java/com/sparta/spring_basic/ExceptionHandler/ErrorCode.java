package com.sparta.spring_basic.ExceptionHandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.*;

@JsonFormat(shape = OBJECT) // Response에서 Enum 객체 전체 반환
@AllArgsConstructor
@Getter
public enum ErrorCode {
    //이미 있는 아이디
    DUPLICATE_ID("DUPLICATE_ID", "Duplicate ID"),

    //ID 또는 PW 조건 불일치
    WRONG_PATTERN("WRONG_PATTERN", "Patterns don't match (ID or PW)"),

    //PW & PW확인 매칭 x
    WRONG_PW_CONFIRM("WRONG_PW_CONFIRM", "PasswordConfirm don't match"),

    //DB에 USER 없음
    NO_USER("NO_USER", "User does not exist"),

    //PW 틀림
    WRONG_PASSWORD("WRONG_PASSWORD", "Password does not match"),

    //권한 없음
    NO_AUTH("NO_AUTH", "You don't have permission"),

    //해당 글이 없음
    NO_POST("NO_POST", "Post is not exist"),

    //해당 코멘트가 없음
    NO_COMMENT("NO_COMMENT", "Comment is not exist");

    private final String code;
    private final String message;
}
