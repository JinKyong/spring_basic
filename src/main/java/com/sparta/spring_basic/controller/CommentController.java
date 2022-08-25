package com.sparta.spring_basic.controller;

import com.sparta.spring_basic.dto.CommentRequestDto;
import com.sparta.spring_basic.dto.ResponseDto;
import com.sparta.spring_basic.repository.CommentRepository;
import com.sparta.spring_basic.security.UserDetailsImpl;
import com.sparta.spring_basic.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{id}")
    public ResponseDto<?> getComments(@PathVariable Long id){ return commentService.getAllComments(id); }

    @PostMapping("/write")
    public ResponseDto<?> writeComment(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        String username = userDetails.getUser().getNickname();
        return commentService.writeComment(requestDto, username);
    }

    @PutMapping("/update/{id}")
    public ResponseDto<?> updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        String username = userDetails.getUser().getNickname();
        return commentService.updateComment(id, requestDto, username);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto<?> deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        String username = userDetails.getUser().getNickname();
        return commentService.deleteComment(id, username);
    }
}
