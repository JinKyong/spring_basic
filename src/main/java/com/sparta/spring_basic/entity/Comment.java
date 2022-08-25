package com.sparta.spring_basic.entity;

import com.sparta.spring_basic.dto.CommentRequestDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 생성, 접근 권한 protected 지정
@Entity
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private Long post_id;

    public Comment(CommentRequestDto requestDto, Long post_id) {
        this.username = requestDto.getUsername();
        this.content = requestDto.getContent();
        this.post_id = post_id;
    }

    public void update(CommentRequestDto requestDto){
        this.content = requestDto.getContent();
    }
}
