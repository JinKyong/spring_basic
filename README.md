Innovation_w03 (Spring Basic Week)
==================================
개인과제 : 게시판 CRUD API 구현(Spring-Boot)

<br/>

🕐프로젝트 기간
==================================
2022.08.12 ~ 2022.08.18 (7일)

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
-----------------
| 기능              | Method | url               | Request             | Response             |
|-----------------  |--------|-------------------|---------------------|---------------------|
| 전체 게시글 목록 조회| GET  | /writings          |   -                       | List(Writing) |
| 게시글 작성        | POST  | /writing           | {"title":"title", "content":"content", "author":"author", "password":"password"} | Writing                    |
| 게시글 조회        | GET   | /writing/{id}      |   -                        | Writing        |
| 게시글 수정        | PUT   | /writing/{id}      | {"title":"title", "content":"content", "author":"author", "password":"password"} | Writing                    |
| 게시글 삭제        | DELETE| /writing/{id}      |   -                        | Long id                    |
| 게시글 비밀번호 확인 | POST  | /writing/check/{id}| {"password":"password"}   |  boolean             |

<br/>

❓ **Why**
-----------------
1. 수정, 삭제 API의 request를 어떤 방식으로 사용하셨나요? (param, query, body)

    > 수정에는 param과 body, 삭제에는 param을 사용하였다.

2. 어떤 상황에 어떤 방식의 request를 써야하나요?

    > request를 식별해야하는 상황에서는 Path Variable(param)
    > 정렬이나 필터링을 해야하는 상황에서는 Query (query)가 더 적합하다.
    
    > body같은 경우에는 인수에 키-값 구조가 없는 경우, 직렬화 된 이진 데이터와 같이 사람이 읽을 수 없는 경우, 또는 매우 많은 수의 인수를 보낼 때 사용하면 좋다.

3. RESTful한 API를 설계했나요? 어떤 부분이 그런가요? 어떤 부분이 그렇지 않나요?

    > HTTP Url을 통해 자원을 명시하고, HTTP method(POST, GET, PUT, DELETE)를 통해 해당 자원에 대한 CRUD OPERATION을 적용하였기 때문에 RESTful한 API라고 생각한다.
    > 즉, HTTP Method를 통해 Resource를 처리하도록 설계하였다(Controller에서 주로 사용).

4. 적절한 관심사 분리를 적용하였나요? (Controller, Repository, Service)

    > Controller는 적절한 Service를 호출해서 매핑하는 역할을
    > Repository는 Database에서 원하는 Data를 뽑아오는(또는 삽입하는) 역할을
    > Service에는 비즈니스 로직을 수행하는 역할을 분배하여 관심사 분리를 적용하였다.

5. 작성한 코드에서 빈(Bean)을 모두 찾아보세요!

    > WritingController, WritingService, WritingRepository
