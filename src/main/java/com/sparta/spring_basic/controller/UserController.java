package com.sparta.spring_basic.controller;
import com.sparta.spring_basic.dto.LoginRequestDto;
import com.sparta.spring_basic.dto.ResponseDto;
import com.sparta.spring_basic.dto.SignupRequestDto;
import com.sparta.spring_basic.entity.User;
import com.sparta.spring_basic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 가입
    @PostMapping("/signup")
    public ResponseDto<?> signup(@RequestBody SignupRequestDto requestDto) {
        return userService.signUp(requestDto);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseDto<?> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse response) {
        return userService.login(requestDto, response);
    }

    //유저모두보기
    @GetMapping()
    public ResponseDto<?> getAllUsers(){
        return userService.getAllUsers();
    }
}