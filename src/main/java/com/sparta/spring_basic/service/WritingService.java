package com.sparta.spring_basic.service;

import static com.sparta.spring_basic.ExceptionHandler.ErrorCode.*;
import com.sparta.spring_basic.dto.ResponseDto;
import com.sparta.spring_basic.dto.WritingRequestDto;
import com.sparta.spring_basic.entity.Writing;
import com.sparta.spring_basic.repository.CommentRepository;
import com.sparta.spring_basic.repository.WritingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WritingService {
    private final WritingRepository writingRepository;
    private final CommentRepository commentRepository;

    //게시글 작성
    @Transactional
    public ResponseDto<?> writePost(WritingRequestDto requestDto, String username){
        Writing writing = new Writing(requestDto, username);  //받은 Dto로 entity 생성
        writingRepository.save(writing);
        return ResponseDto.success(writing);
    }

    //전체 게시글 조회
    public ResponseDto<?> getAllPosts() {
        return ResponseDto.success(writingRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")));
    }

    //id로 게시글 조회
    public ResponseDto<?> getPost(Long id){
        Optional<Writing> optionalWriting = writingRepository.findById(id);
        if(optionalWriting.isEmpty()){
            return ResponseDto.fail(NO_POST);
        }

        return ResponseDto.success(optionalWriting.get());
    }

//    public boolean checkPW(Long id, String password) {
//        Writing writing = writingRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//        );
//        return writing.getPassword().equals(password);
//    }

    //게시글 수정
    @Transactional
    public ResponseDto<?> updatePost(Long id, WritingRequestDto requestDto, String username){
        Optional<Writing> optionalWriting = writingRepository.findById(id);
        if(optionalWriting.isEmpty()){
            return ResponseDto.fail(NO_POST);
        }

        Writing writing = optionalWriting.get();
        if(!writing.getAuthor().equals(username)){
            return ResponseDto.fail(NO_AUTH);
        }

        writing.update(requestDto);
        return ResponseDto.success(writing);
    }

    //게시글 삭제
    @Transactional
    public ResponseDto<?> deletePost(Long id, String username){
        Optional<Writing> optionalWriting = writingRepository.findById(id);
        if(optionalWriting.isEmpty()){
            return ResponseDto.fail(NO_POST);
        }

        Writing writing = optionalWriting.get();
        if(!writing.getAuthor().equals(username)){
            return ResponseDto.fail(NO_AUTH);
        }

        commentRepository.deleteAllByWritingID(id);
        writingRepository.deleteById(id);
        return ResponseDto.success(id);
    }
}
