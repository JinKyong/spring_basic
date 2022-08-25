package com.sparta.spring_basic.controller;

import com.sparta.spring_basic.dto.ResponseDto;
import com.sparta.spring_basic.dto.WritingRequestDto;
import com.sparta.spring_basic.entity.Writing;
import com.sparta.spring_basic.security.UserDetailsImpl;
import com.sparta.spring_basic.service.WritingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/writing")
public class WritingController {
    private final WritingService writingService;

    @PostMapping()
    public ResponseDto<?> writePost(@RequestBody WritingRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUsername();
        return writingService.writePost(requestDto, username);
    }

    @GetMapping()
    public ResponseDto<?> getAllPosts() {
        return writingService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseDto<?> getPost(@PathVariable Long id){
        return writingService.getPost(id);
    }

    @PutMapping("/{id}")
    public ResponseDto<?> updatePost(@PathVariable Long id, @RequestBody WritingRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUsername();
        return writingService.updatePost(id, requestDto, username);
    }

    @DeleteMapping("/{id}")
    public ResponseDto<?> deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        String username = userDetails.getUsername();
        return writingService.deletePost(id, username);
    }

//    @PostMapping("/check/{id}")
//    public boolean checkPW(@PathVariable Long id, @RequestBody String password){
//        return writingService.checkPW(id, password);
//    }
}
