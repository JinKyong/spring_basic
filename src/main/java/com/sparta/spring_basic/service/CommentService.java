package com.sparta.spring_basic.service;

import static com.sparta.spring_basic.ExceptionHandler.ErrorCode.*;
import com.sparta.spring_basic.dto.CommentRequestDto;
import com.sparta.spring_basic.dto.ResponseDto;
import com.sparta.spring_basic.entity.Comment;
import com.sparta.spring_basic.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public ResponseDto<?> getAllComments(Long id){
        return ResponseDto.success(commentRepository.findAllByWritingIDOrderByModifiedAtDesc(id));
    }

    public ResponseDto<?> writeComment(CommentRequestDto requestDto, String username){
        Comment comment = new Comment(requestDto, username);
        commentRepository.save(comment);
        return ResponseDto.success(comment);
    }

    public ResponseDto<?> updateComment(Long id, CommentRequestDto requestDto, String username){
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if(optionalComment.isEmpty()){
            return ResponseDto.fail(NO_COMMENT);
        }

        Comment comment = optionalComment.get();
        if(!username.equals(comment.getUsername())){
            return ResponseDto.fail(NO_AUTH);
        }

        comment.update(requestDto);

        return ResponseDto.success(comment);
    }

    public ResponseDto<?> deleteComment(Long id, String username){
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if(optionalComment.isEmpty()){
            return ResponseDto.fail(NO_COMMENT);
        }

        Comment comment = optionalComment.get();
        if(!username.equals(comment.getUsername())){
            return ResponseDto.fail(NO_AUTH);
        }

        commentRepository.deleteById(id);

        return ResponseDto.success(id);
    }
}
