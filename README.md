Innovation_w03 (Spring Basic Week)
==================================
개인과제 : 게시판 CRUD API 구현(Spring-Boot)

<br/>

🕐프로젝트 기간
==================================
2022.08.12 ~ 2022.0.18 (7일)

<br/>

🔒요구사항
==================================
1. 아래의 요구사항을 기반으로 Use Case 그려보기

    + 손으로 그려도 됩니다.
    + cf. https://narup.tistory.com/70
  
2. 전체 게시글 목록 조회 API

    +  제목, 작성자명, 작성 날짜를 조회하기
    +  작성 날짜 기준으로 내림차순 정렬하기

3. 게시글 작성 API

    + 제목, 작성자명, 비밀번호, 작성 내용을 입력하기!

4. 게시글 조회 API

    + 제목, 작성자명, 작성 날짜, 작성 내용을 조회하기 (검색 기능이 아닙니다. 간단한 게시글 조회만 구현해주세요.)

5. 게시글 비밀번호 확인 API

    + 비밀번호를 입력 받아 해당 게시글의 비밀번호와 일치여부 판단하기

6. 게시글 수정 API

    + 제목, 작성자명, 비밀번호, 작성 내용을 수정되게 하기

7. 게시글 삭제 API

    + 글이 삭제되게 하기

<br/>

Use Case
-----------------
![innovation_w3_usecase](https://user-images.githubusercontent.com/48237998/185523420-b5dd9fe0-73bb-49f6-901f-3676acf3dfa7.png)

<br/>

API 명세서
| 기능              | Method | url               | Request             | Response             |
|-----------------  |--------|-------------------|---------------------|---------------------|
| 전체 게시글 목록 조회| GET  | /writings          |   -                       | List<Writing> |
| 게시글 작성        | POST  | /writing           | {"title":"title", "content":"content", "author":"author", "password":"password"} | Writing                    |
| 게시글 조회        | GET   | /writing/{id}      |   -                        | Writing        |
| 게시글 수정        | PUT   | /writing/{id}      | {"title":"title", "content":"content", "author":"author", "password":"password"} | Writing                    |
| 게시글 삭제        | DELETE| /writing/{id}      |   -                        | Long id                    |
| 게시글 비밀번호 확인 | POST  | /writing/check/{id}| {"password":"password"}   |  boolean             |
