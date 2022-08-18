package com.sparta.spring_basic.controller;

import com.sparta.spring_basic.dto.WritingRequestDto;
import com.sparta.spring_basic.entity.Writing;
import com.sparta.spring_basic.repository.WritingRepository;
import com.sparta.spring_basic.service.WritingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class WritingController {

    private final WritingService writingService;

    @PostMapping("/writing")
    public Writing writePost(@RequestBody WritingRequestDto requestDto) {
        return writingService.writePost(requestDto);
    }

    @GetMapping("/writings")
    public List<Writing> getAllPosts() {
        return writingService.getAllPosts();
    }

    @GetMapping("/writing/{id}")
    public Writing getPost(@PathVariable Long id){
        return writingService.getPost(id);
    }

    @PutMapping("/writing/{id}")
    public Writing updatePost(@PathVariable Long id, @RequestBody WritingRequestDto requestDto) {
        return writingService.updatePost(id, requestDto);
    }

    @DeleteMapping("/writing/{id}")
    public Long deletePost(@PathVariable Long id){
        return writingService.deletePost(id);
    }
}
