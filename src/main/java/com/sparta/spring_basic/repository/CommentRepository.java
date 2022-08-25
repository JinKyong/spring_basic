package com.sparta.spring_basic.repository;

import com.sparta.spring_basic.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByWritingIDOrderByModifiedAtDesc(Long writingID);

    void deleteAllByWritingID(Long writingID);
}