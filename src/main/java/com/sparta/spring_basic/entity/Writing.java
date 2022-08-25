package com.sparta.spring_basic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.spring_basic.dto.WritingRequestDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 생성, 접근 권한 protected 지정
@Entity // DB 테이블 역할
public class Writing extends Timestamped{
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동 증가 명령입니다.
    private Long id;
    @Column(nullable = false)   // 컬럼 값이고 반드시 값이 존재해야 함을 나타냅니다.
    private String title;       // 제목
    @Column(nullable = false)
    private String content;     // 내용
    @Column(nullable = false)
    private String author;      // 작성자
    @Column
    @OneToMany(cascade = CascadeType.REMOVE)    //글 삭제시 코멘트도 모두 삭제
    private List<Comment> comments;

//    @Column(nullable = false)
//    @JsonIgnore // 응답에 해당 데이터 포함하지 않음
//    private String password;       // 비밀번호


    public Writing(WritingRequestDto requestDto, String author){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.author = author;
        //this.password = requestDto.getPassword();
    }

    public void update(WritingRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        //this.author = requestDto.getAuthor();
        //this.password = requestDto.getPassword();
    }
}
