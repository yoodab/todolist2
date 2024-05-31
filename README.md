# 나만의 일정 관리 앱 서버 만들기
프로그램 역할 : 나만의 일정을 관리하는 앱 서버
* 프로젝트 기간 : 2024.05.30 ~ 2024.05.31 (2일)
* 참여자 : 윤다빈
<br>

## 프로젝트 소개
<details>
<summary> 📑 요구사항 </summary>

### 1️⃣단계 - 일정과 댓글의 연관 관계

- 설명
  - 지난 과제에서 만든 일정에 댓글을 추가할 수 있습니다.
  - ERD에도 댓글 모델을 추가합니다.
  - 각 일정에 댓글을 작성할 수 있도록 관련 클래스를 추가하고 연관 관계를 설정합니다.
  - 매핑 관계를 설정합니다. (1:1 or N:1 or N:M)

### 2️⃣단계 - 댓글 등록 : 선택한 일정이 있다면 댓글을 등록합니다.

##### 조건

    - 댓글이 등록되었다면 client에게 반환합니다.
    - 선택한 일정이 DB에 저장되어 있어야 합니다.
    - 댓글을 식별하는 `고유번호`, `댓글 내용`, 댓글을 작성한 `사용자 아이디`, 댓글이 작성된 `일정 아이디`, `작성일자`를 저장할 수 있습니다.
    
#### 예외 처리
    
    - 선택한 일정의 ID를 입력 받지 않은 경우
    - 댓글 내용이 비어 있는 경우
    - 일정이 DB에 저장되지 않은 경우
### 3️⃣단계 - 댓글 수정 : 선택한 일정의 댓글을 수정합니다.

#### 조건

    - 댓글이 수정되었다면 수정된 댓글을 반환합니다.
    - `댓글 내용`만 수정 가능합니다.
    - 선택한 일정과 댓글이 DB에 저장되어 있어야 합니다.

#### 예외 처리

    - 선택한 일정이나 댓글의 ID를 입력 받지 않은 경우
    - 일정이나 댓글이 DB에 저장되지 않은 경우
    - 선택한 댓글의 사용자가 현재 사용자와 일치하지 않은 경우

### 4️⃣단계 - 댓글 삭제 : 선택한 일정의 댓글을 삭제합니다.

#### 조건
    - 성공했다는 메시지와 상태 코드 반환하기
    - 선택한 일정과 댓글이 DB에 저장되어 있어야 합니다.

#### 예외 처리
    - 선택한 일정이나 댓글의 ID를 입력받지 않은 경우
    - 일정이나 댓글이 DB에 저장되지 않은 경우
    - 선택한 댓글의 사용자가 현재 사용자와 일치하지 않은 경우

### 5️⃣단계 - JWT : JWT를 이용한 인증/인가를 구현한다

#### 조건
    - 위 1~4 단계에서 인증/인가가 완료된 후에만 기능이 동작하도록 수정한다.
    - Access Token 만료시간 60분
    - Refresh Token 구현은 8단계이므로 이번에는 하지 않습니다.

#### 예외 처리
    - 공통조건
    - StatusCode : 400
    - client에 반환

    - 토큰이 필요한 API 요청에서 토큰을 전달하지 않았거나 정상 토큰이 아닐 때
        에러 메세지 : 토큰이 유효하지 않습니다.

    - 토큰이 있고, 유효한 토큰이지만 해당 사용자가 작성한 게시글/댓글이 아닐 때
        에러 메세지 : 작성자만 삭제/수정할 수 있습니다.

    - DB에 이미 존재하는 `username`으로 회원가입을 요청할 때
        에러 메세지 : 중복된 `username` 입니다.

    - 로그인 시, 전달된 `username`과 `password` 중 맞지 않는 정보가 있을 때
        에러 메시지 : 회원을 찾을 수 없습니다.

### 6️⃣단계 - 회원가입 : 사용자의 정보를 전달 받아 유저 정보를 저장한다.

#### 조건
    - 패스워드 암호화는 하지 않습니다.
    - `username`은  `최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)`로 구성되어야 한다.
    - `password`는  `최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9)`로 구성되어야 한다.
    - DB에 중복된 `username`이 없다면 회원을 저장하고 Client 로 성공했다는 메시지, 상태코드 반환하기

### 7️⃣단계 - 로그인 : username, password 정보를 client로부터 전달받아 토큰을 반환한다.

#### 설명
    - DB에서 `username`을 사용하여 저장된 회원의 유무를 확인한다.
    - 저장된 회원이 있다면 `password` 를 비교하여 로그인 성공 유무를 체크한다.

#### 조건
    - 패스워드 복호화는 하지 않습니다.
    - 로그인 성공 시 로그인에 성공한 유저의 정보와 JWT를 활용하여 토큰을 발급한다.
    - 발급한 토큰을 `Header`에 추가하고 성공했다는 메시지 및 상태코드와 함께 client에 반환한다.


</details>

<details>
<summary> 📏ERD </summary>
  <p align="center">
  <img src="https://github.com/yoodab/todolist2/assets/109517088/c1119113-75dc-4b92-808d-70581ef3afff">
</p>
</details>


<details>
<summary>🧾 API 명세서 </summary>
  [API 명세서](https://documenter.getpostman.com/view/23784910/2sA3JT1xdL)
  
<br>
  <img src="https://github.com/yoodab/todoList/assets/109517088/06662f39-2f34-4215-ae30-cacad5d17b35" height="80%"/>
</details>


<br>


## 🏗 프로젝트 구조
```
├─main
│  ├─java
│  │  └─com
│  │      └─sparta
│  │          └─todolist
│  │              │  TodoListApplication.java
│  │              │
│  │              ├─config
│  │              │      SwaggerConfig.java
│  │              │      WebSecurityConfig.java
│  │              │
│  │              ├─controller
│  │              │      CommentController.java
│  │              │      TodoController.java
│  │              │      UserController.java
│  │              │
│  │              ├─dto
│  │              │      CommentRequestDto.java
│  │              │      CommentResponseDto.java
│  │              │      LoginRequestDto.java
│  │              │      SignupRequestDto.java
│  │              │      SignupResponseDto.java
│  │              │      TodoRequestDto.java
│  │              │      TodoResponseDto.java
│  │              │
│  │              ├─entity
│  │              │      Comment.java
│  │              │      Timestamped.java
│  │              │      Todo.java
│  │              │      User.java
│  │              │      UserRoleEnum.java
│  │              │
│  │              ├─exception
│  │              │  │  InvalidPasswordException.java
│  │              │  │  TodoExceptionHandler.java
│  │              │  │  TodoNotFoundException.java
│  │              │  │
│  │              │  └─message
│  │              │          ErrorMessage.java
│  │              │          Message.java
│  │              │          StatusEnum.java
│  │              │
│  │              ├─jwt
│  │              │      CustomAuthenticationProvider.java
│  │              │      JwtAuthenticationFilter.java
│  │              │      JwtAuthorizationFilter.java
│  │              │      JwtUtil.java
│  │              │
│  │              ├─repository
│  │              │      CommentRepository.java
│  │              │      TodoRepository.java
│  │              │      UserRepository.java
│  │              │
│  │              ├─security
│  │              │      UserDetailsImpl.java
│  │              │      UserDetailsServiceImpl.java
│  │              │
│  │              └─service
│  │                      CommentService.java
│  │                      TodoService.java
│  │                      UserService.java
│  │
│  └─resources
│          application.properties
│
└─test
    └─java
        └─com
            └─sparta
                └─todolist
                        TodoListApplicationTests.java

```
<br>
