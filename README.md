# 📚 도서 관리 시스템

> KT AIVLE School AI 트랙 미니 프로젝트 5차 12조 BackEnd


<br>

## 📌 목차

- [프로젝트 소개](#-프로젝트-소개)
- [개발 기간](#-개발-기간)
- [팀 R&R](#-팀-rr)
- [기술 스택](#-기술-스택)
- [ERD](#-erd)
- [프로젝트 구조](#-프로젝트-구조)
- [API 엔드포인트](#-api-엔드포인트)
- [기능 설명](#-기능-설명)
- [설치 및 실행 방법](#-설치-및-실행-방법)
- [화면 미리보기](#-화면-미리보기)
- [트러블슈팅](#-트러블슈팅)

<br>

## 🖥 프로젝트 소개

<!-- 프로젝트 배경, 목적, 간단한 설명을 작성하세요 -->
기존 Frontend 미니프로젝트(json-server 기반)를 분석하여, 실제 Spring Boot + MySQL 백엔드로 전환하는 프로젝트입니다.

### Before — Frontend 단독 구조 (json-server)

```mermaid 
graph TD
    User(("👤 사용자"))
    Client["💻 FrontEnd<br>(React + Vite)"]
    AI["🤖 OpenAI API"]
    Server["🗄️ BackEnd<br>(json-server)"]
 
    User -- "1. 도서 등록/수정 내용 및<br>표지 프롬프트 입력" --> Client
    Client -- "2. 표지 프롬프트 전송" --> AI
    AI -- "3. 생성된 이미지 URL 반환" --> Client
    Client -- "4. 도서 정보 + 이미지 URL<br>최종 저장 요청 (POST/PATCH)" --> Server
    Server -- "5. 저장 완료 및 데이터 응답" --> Client
```

### After — FullStack 구조 (Spring Boot + MySQL)

```mermaid
graph LR
    User(("🖥 클라이언트"))
    Client["⚛️ React"]
    Server["🌿 Spring Boot"]
    DB[("🛢 DB")]
    AI["🤖 OpenAI"]
 
    User -- "HTTP Protocol<br>UI 렌더링" --> Client
    Client -- "REST API 및 fetch<br>도서 조회 등" --> Server
    Client -- "Data URL(base64) 저장" --> Server
    Server -- "JSON 응답" --> Client
    Server -- "JPA" --> DB
    DB -- "도서정보·이미지 URL<br>업데이트 후 저장" --> Server
    Client -- "OpenAI API 호출<br>(API Key 사용)" --> AI
    AI -- "이미지 데이터(base64) 반환" --> Client
```

<br>

## 📅 개발 기간

2026.06.09 ~ 2026.06.12

<br>

## 👥 팀 R&R

| 이름 | 역할           | 담당 기능        |
|------|--------------|--------------|
| 박태정 | 조장  | PM·기획, AI/Frontend 연동 |
| 김다진 | PPT          | 백엔드 개발 (1)   |
| 황민서 | 검토담당자        | 백엔드 개발 (2)   |
| 배수성 | 타임키퍼         | 백엔드 개발 (2)   |
| 유지은 | 발표자          | 백엔드 개발 (3)   |
| 이채은 | 서기           | AI/Frontend 연동 |
| 김다애 | PPT          | 통합/예외 처리     |

<br>

## 🛠 기술 스택

### Environment
<img src="https://img.shields.io/badge/intellij idea-000000?style=for-the-badge&logo=intellijidea&logoColor=white" alt=""> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white" alt=""> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white" alt="">

### Backend
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=openjdk&logoColor=white" alt=""> <img src="https://img.shields.io/badge/spring boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt=""> <img src="https://img.shields.io/badge/spring mvc-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt=""> <img src="https://img.shields.io/badge/spring data jpa-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt=""> <img src="https://img.shields.io/badge/lombok-CA0124?style=for-the-badge&logo=lombok&logoColor=white" alt=""> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="">

### Frontend
<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black" alt=""> <img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=black" alt=""> <img src="https://img.shields.io/badge/vite-9135FF?style=for-the-badge&logo=vite&logoColor=black" alt=""> <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white" alt=""> <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white" alt=""> <img src="https://img.shields.io/badge/OpenAI API-412991?style=for-the-badge&logo=openai&logoColor=white" alt="">

### Communication
<img src="https://img.shields.io/badge/figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white" alt=""> <img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white" alt=""> <img src="https://img.shields.io/badge/zoom-0B5CFF?style=for-the-badge&logo=zoom&logoColor=white" alt=""> <img src="https://img.shields.io/badge/Microsoft Teams-6264A7?style=for-the-badge&logo=microsoftteams&logoColor=white" alt="">

<br>

## 🗂 ERD

<!-- ERD 이미지를 아래에 첨부하세요 -->
<!-- ![ERD](./docs/erd.png) -->

```
USERS
- id (PK)               유저 고유번호
- email (UK)            이메일 (로그인 ID)      @NotNull @Unique
- password              암호화된 비밀번호        @NotNull
- nickname (UK)         닉네임                  @NotNull @Unique
- created_at                                    @PrePersist 자동 설정
 
PROFILES
- id (PK, FK)           유저 고유번호 (1:1)      @MapsId
- bio                   자기소개                 @NotNull
- avatar                아바타 이미지 주소        @NotNull
- created_at                                    @PrePersist 자동 설정
 
BOOK
- id (PK)               도서 고유번호
- author_id             작가(User) 고유번호
- title                 도서 제목
- author                작가명
- content (LONGTEXT)    도서 본문 / 소개 내용
- cover_image_url       AI 생성 표지 이미지 URL  (LONGTEXT)
- genre                 장르
- publisher             출판사
- price                 가격
- pages                 페이지 수
- isbn                  ISBN 번호
- pub_date              출판일
- view_count            조회수                  @PrePersist 기본값 0
- created_at                                    @PrePersist 자동 설정
- updated_at                                    @PreUpdate 자동 갱신
 
FAVORITE
- id (PK)               즐겨찾기 고유번호
- user_id (FK)          유저 고유번호            @NotNull
- book_id (FK)          도서 고유번호            @NotNull
- created_at                                    @PrePersist 자동 설정
* UNIQUE (user_id, book_id)
 
FOLLOW
- id (PK)               팔로우 고유번호
- follower_id           팔로우 하는 유저 ID      @NotNull
- following_id          팔로우 대상 유저 ID      @NotNull
- created_at
 
COMMENT
- id (PK)               댓글 고유번호
- book_id               도서 고유번호            @NotNull
- user_id               유저 고유번호            @NotNull
- content               댓글 내용               @NotNull
- rating                별점 (1~5)              @NotNull
- created_at
- updated_at
```

<br>

## 📂 프로젝트 구조

```
book-backend/src/main/java/com/aivle12/book_backend/
├── domain/
│   ├── Book.java                   # 도서 Entity (@PrePersist/@PreUpdate 자동 시간 설정, 조회수)
│   ├── Comment.java                 # 댓글 Entity (bookId, userId, rating)
│   ├── Favorite.java                # 즐겨찾기 Entity (UNIQUE: user_id + book_id)
│   ├── Follow.java                  # 팔로우 Entity (followerId, followingId)
│   ├── Profile.java                 # 프로필 Entity (User와 1:1, bio/avatar/createdAt)
│   └── User.java                    # 회원 Entity (email, nickname Unique)
├── dto/                              # 요청/응답 DTO (Entity 직접 노출 방지, XxxDto → Xxx 네이밍 통일)
├── repository/                       # JpaRepository 상속, 도메인별 CRUD/조회 메서드
│   ├── BookRepository.java          # findByAuthorId (작가별 도서 목록 필터링)
│   ├── CommentRepository.java       # averageRatingByBookId, countByBookId (평점 집계)
│   ├── FavoriteRepository.java
│   ├── FollowRepository.java
│   ├── ProfileRepository.java       # findByUserEmail 등 User 이메일 기반 조회
│   └── UserRepository.java
├── service/
│   ├── AuthService.java             # 회원가입/로그인/내정보, 비밀번호 암호화·JWT 발급
│   ├── AuthorService.java           # 회원(User) CRUD - 작가 정보 관리
│   ├── BookService.java             # 도서 CRUD, 조회수 증가, 표지 변경, 평점/평점수 포함 응답, 작성자 권한 검증 (@Transactional)
│   ├── AiCoverService.java          # OpenAI 이미지 생성 API 호출, 표지 3장 생성
│   ├── CommentService.java          # 댓글 CRUD
│   ├── FavoriteService.java         # 즐겨찾기 등록/삭제 (JWT userId 기반)
│   ├── FollowService.java           # 팔로우/언팔로우, 팔로잉·팔로워 목록, 팔로우 여부 조회
│   └── ProfileService.java          # 프로필 등록/조회/수정/삭제
├── controller/
│   ├── UserController.java          # /users - 회원가입, 로그인, 내정보, ID로 사용자 조회, 이메일/닉네임 중복확인
│   ├── AuthorController.java        # /users - 회원 목록/등록/수정/삭제
│   ├── ProfileController.java       # /users/profile - 프로필 등록/조회/수정/삭제
│   ├── BookController.java          # /books - CRUD(인증), 조회수 증가, authorId 필터링, AI 표지 생성/적용
│   ├── CommentController.java       # /books/{bookId}/comments, /comments/{id}
│   ├── FavoriteController.java      # /books/{bookId}/favorites (인증 필요)
│   └── FollowController.java        # /authors/{id}/follows(+/status), /users/followings|followers
├── security/
│   ├── JwtTokenProvider.java        # JWT 토큰 생성/검증/userId 추출
│   └── JwtAuthenticationFilter.java # 요청마다 JWT 검증 후 SecurityContext에 인증정보 설정
├── exception/                        # 커스텀 예외 + GlobalExceptionHandler(전역 예외 처리)
│   └── ...NotFoundException / ...AlreadyExistsException (Book/Comment/Favorite/Follow/Profile/User)
├── config/
│   ├── SecurityConfig.java          # Spring Security 설정 (JWT 필터, 인가 규칙, BCrypt)
│   ├── WebConfig.java               # CORS 설정 (프론트 localhost:5173 연동)
│   └── RestTemplateConfig.java      # AI 표지 생성용 RestTemplate Bean (connect/read timeout 설정)
└── BookBackendApplication.java

src/main/resources/
└── application.yaml                 # MySQL DB 연결, JPA, JWT, OpenAI API Key 설정
```

<br>

## 🔌 API 엔드포인트

> 📋 [API 명세서 (Notion)](https://app.notion.com/p/API-Docs-cc1302230b42838fa095018595e8b1c7)

<br>

## 💡 기능 설명

| 기능                   | 설명                                                                          |
|----------------------|-----------------------------------------------------------------------------|
| 🔐 회원가입 / 로그인 / 로그아웃 | - JWT 기반 인증을 통해 사용자 회원가입, 로그인, 로그아웃 기능 제공 <br> - 로그인 후 마이페이지와 사용자별 기능 접근 가능 |
| 👤 개인 프로필 관리         | - 사용자별 프로필 페이지 기능 제공 <br> - 마이페이지에서 등록한 책, 즐겨찾기, 팔로우 확인 가능                  |
| 🤝 팔로우 / 언팔로우        | - 사용자 간 팔로우 및 언팔로우 기능 제공 <br> - 팔로워·팔로잉 목록을 통해 사용자 간 연결 관계 확인 가능            |
| ⭐ 댓글 기반 평점           | - 도서별 댓글 작성과 함께 평점 등록 기능 제공 <br> - 사용자 리뷰를 기반으로 도서 평가와 피드백 확인 가능            |
| 🔎 상세 검색             | - 제목, 작가 외에도 출판사, 가격, 리뷰 평점, 출판연도 별로 필터링해서 검색 가능                            |

<br>

## ⚙️ 설치 및 실행 방법

### 사전 요구사항

- Java 17+
- Node.js / npm
- MySQL

### 환경 변수 설정

**Backend** — 프로젝트 루트에 `.env` 파일 생성:
```
DB_HOST=localhost
DB_PORT=3306
DB_NAME=book_db
DB_USERNAME=root
DB_PASSWORD=
OPENAI_API_KEY=
```

**Frontend** — 프론트엔드 루트에 `.env` 파일 생성:
```
VITE_API_BASE_URL=http://localhost:8080
```

### Backend 실행

```bash
# 1. 저장소 클론
$ git clone https://github.com/aivleschool-miniproject12/BackEnd.git
 
# 2. 디렉토리 이동
$ cd BackEnd

IntelliJ에서 `BookappApplication.java` 실행
```

### Frontend 실행

```bash
$ git clone https://github.com/aivleschool-miniproject12/Frontend.git
$ npm install
$ npm run dev
```

<br>

## 🖼 화면 미리보기

| 페이지      | 미리보기 |
|----------|------|
| 회원가입     | <img width="1242" height="1363" alt="Image" src="https://github.com/user-attachments/assets/ddcc1972-fa6e-44da-8d88-717bdcaae22d" />     |
| 로그인      | <img width="1247" height="1367" alt="Image" src="https://github.com/user-attachments/assets/fd36af37-62b3-4b84-8e02-ceb68c955082" />     |
| 상세 검색 토글 | <img width="1235" height="635" alt="Image" src="https://github.com/user-attachments/assets/79a6e645-d86c-439a-aa89-d25154955675" />     |
| 작가 프로필   | <img width="1248" height="950" alt="Image" src="https://github.com/user-attachments/assets/6e6684f3-4147-40f6-8500-3f03d5436407" />     |
| 댓글·리뷰 등록 | <img width="1242" height="1386" alt="Image" src="https://github.com/user-attachments/assets/5860a864-b555-465e-ab03-9c77b80ba548" />     |
| 팔로우 신청   | <img width="1249" height="924" alt="Image" src="https://github.com/user-attachments/assets/ca4a1aac-6ceb-481e-9137-936b1aad965f" />     |

<br>

## 🔧 트러블슈팅

| 이슈 | 원인 | 해결 방법 |
|------|------|-----------|
| 수동 코드 리뷰 중 충돌 및 오류 발생 | 여러 브랜치 동시 작업 시 수동 리뷰로는 충돌 감지가 늦음 | GitHub에 CodeRabbit AI 코드 리뷰 도구 연동하여 PR마다 자동 리뷰 적용 |
| 상세 검색에서 가격 표기법 전환 문제 | 가격 입력 자릿수 제한 없음 | `.slice(0, 9)` 로 9자리 입력 제한 적용 |
| 별점 필터 미적용 문제 | 별점 값을 별도 계산하면서 필터에 반영되지 않음 | 별도 계산 없이 `book.averageRating` 을 직접 사용하도록 변경 |
| 병합 과정에서 파일명 충돌 발생 | 기능별로 Controller·Service·Repository·Domain 을 각자 작성하면서 일부 파일명이 다른 팀원과 중복 | 기능 확인 후 중복된 파일명 변경 |
| 유저 객체와 프로필 객체가 1:1 대응하지 못하는 문제 | Profile 객체의 id가 자동 생성되어 User 와 매핑되지 않음 | Profile id 자동 생성을 제거하고 User 에 1:1 대응(`@MapsId`)하도록 수정 |
| `ProfileRepository.findByEmail()` 사용 시 애플리케이션 기동 실패 | Profile 엔티티에 `email` 필드가 없는데 파생 쿼리를 생성하려 해서 `BeanCreationException` 발생 | `findByEmail()` 을 `findByUserEmail()` 로 변경하여 연관 엔티티(User.email)를 통해 조회하도록 수정 |
| develop 브랜치 병합 시 API 경로 충돌 | 다른 조원 코드와 동일한 `/users` 경로 사용 | 인증 기능은 `AuthController` 로 분리, 프로필 기능은 `UserController` 에서 담당하도록 변경 |
| 댓글 작성자 표시 시 닉네임 대신 userId 노출 | `CommentResponse` DTO에 `nickname` 필드가 없어 userId만 반환 | `nickname` 필드 추가 및 `UserRepository` 를 통한 닉네임 조회 로직 구현 예정 |
| 중복 파일 생성 및 오경로 파일 GitHub Push 오류 | 디렉토리 구조 혼선 | 전체 디렉토리 아키텍처 재정비 및 정확한 Git 명령어를 통한 파일 관리 정상화 |
| 댓글 수정 요청 시 데이터 미반영 | 프론트엔드 API 엔드포인트와 백엔드 라우팅 경로 간 미스매치 | 엔드포인트 정합성 일치로 해결 |