package com.sparta.spring_basic.service;

import com.sparta.spring_basic.dto.WritingRequestDto;
import com.sparta.spring_basic.entity.Writing;
import com.sparta.spring_basic.repository.WritingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WritingService {

    private final WritingRepository writingRepository;

    //게시글 작성
    public Writing writePost(WritingRequestDto requestDto){
        Writing writing = new Writing(requestDto);  //받은 Dto로 entity 생성
        return writingRepository.save(writing);
    }

    //전체 게시글 조회
    public List<Writing> getAllPosts() {
        return writingRepository.findAllByOrderByIdAtDesc();
    }

    //id로 게시글 조회
    public Writing getPost(Long id){
        return writingRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
    }

    //게시글 수정
    @Transactional
    public Writing updatePost(Long id, WritingRequestDto requestDto){
        Writing writing = writingRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        writing.update(requestDto);
        return writing;
    }

    //게시글 삭제
    public Long deletePost(Long id){
        writingRepository.deleteById(id);
        return id;
    }
}
