# Web-Vue-Spring

## 프로젝트 소개
Vue와 Spring 연습을 위한 웹 사이트로 SPA 아키텍처 설계를 기반으로 MVC 디자인 패턴으로 구현 중인 웹 사이트

## 개발 환경
> Tools  
![sts](https://img.shields.io/badge/sts-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![vscode](https://img.shields.io/badge/vscode-669DF6?style=for-the-badge&logo=vscode&logoColor=white)
![docker](https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![dbeaver](https://img.shields.io/badge/dbeaver-382923?style=for-the-badge&logo=dbeaver&logoColor=white)
![gradle](https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)
![github](https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white)  
> DB  
![MySQL](https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white)  
> Server  
![apache_tomcat](https://img.shields.io/badge/apache_tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=black)  
> Programming Language  
![JAVA](https://img.shields.io/badge/JAVA-007396?style=for-the-badge&logo=java&logoColor=white)
![html](https://img.shields.io/badge/html-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![css](https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![javascript](https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)  
> Framework  
![spring](https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![vuedotjs](https://img.shields.io/badge/vuedotjs-4FC08D?style=for-the-badge&logo=vuedotjs&logoColor=white)

## 다이어그램과 구현코드
![image](https://github.com/hi-inbeom/Web-Vue-Spring/blob/main/readme-images/Diagram.webp)  
![image](https://github.com/hi-inbeom/Web-Vue-Spring/blob/main/readme-images/Controller.webp)  
> Controller에서는 Presentation Layer의 역할에 맞도록 응답, 암호화, 세션에 대한 코드만 구현하였습니다.

![image](https://github.com/hi-inbeom/Web-Vue-Spring/blob/main/readme-images/Service.webp)  
> Service에서는 불필요한 ServiceImpl을 제외하여 코드의 복잡성을 낮췄고,  
> Guard Clause방식을 활용해 조건문 중첩을 줄여 가독성을 개선했습니다.

![image](https://github.com/hi-inbeom/Web-Vue-Spring/blob/main/readme-images/Repository.webp)  
![image](https://github.com/hi-inbeom/Web-Vue-Spring/blob/main/readme-images/DAO.webp)  
![image](https://github.com/hi-inbeom/Web-Vue-Spring/blob/main/readme-images/DTO.webp)  
> Base 클래스를 사용하여 DB 테이블의 공통 컬럼(인덱스, 등록일, 수정일)을 추상화하여 중복을 제거하였고, User 및 Board 엔티티에서 이를 상속받아 일관된 구조를 유지했습니다.

## 스크린샷
![p1](https://github.com/hi-inbeom/Web-Vue-Spring/blob/main/readme-images/p1.png)
![p2](https://github.com/hi-inbeom/Web-Vue-Spring/blob/main/readme-images/p2.png)
![p3](https://github.com/hi-inbeom/Web-Vue-Spring/blob/main/readme-images/p3.png)
![p4](https://github.com/hi-inbeom/Web-Vue-Spring/blob/main/readme-images/p4.png)
![p5](https://github.com/hi-inbeom/Web-Vue-Spring/blob/main/readme-images/p5.png)
![p6](https://github.com/hi-inbeom/Web-Vue-Spring/blob/main/readme-images/p6.png)

## 추후 계획

### 메인화면
- **markdown-it**을 활용하여 `README.md`를 표시할 예정

### SearchBar
- **검색창 숨기기 기능**  
  - Router를 이용하여 `/infinity`와 `/list`일 경우에만 표시되도록 하여 게시판에서만 검색 기능이 작동되도록 구현할 예정

- **검색 기능**  
  - GET 통신을 이용하고 `RequestParam`으로 검색어를 넘겨줄 예정

### Infinity Board
- **무한 스크롤 기능**  
  - UI는 `v-for`와 `IntersectionObserver`를 이용하고, 통신은 GET을 이용하여 페이지네이션을 통해 한번에 가져올 정보를 전달하여 구현할 예정

- **정렬 기능**  
  - GET 통신을 이용하고 `RequestParam`으로 정렬 정보와 페이지네이션 정보를 넘겨줄 예정

- **게시물 상세보기 기능**  
  - UI는 글쓰기에서 사용했던 모달을 `v-if` 혹은 `component` 태그를 이용하여 수정하고  
  - GET 통신을 이용하여 UI상 보이지 않는 DB의 `idx` 값을 `PathVariable`로 넘겨주어 구현할 예정

- **게시물 수정 기능**  
  - PUT 통신을 이용하고 UI는 글쓰기에서 사용했던 모달을 사용하고  
  - PATCH 통신을 이용하여 구현할 예정

- **게시물 삭제 기능**  
  - 로그인 Session을 이용하여 DELETE 메서드와 `PathVariable`로 구현할 예정

- **좋아요 기능**  
  - `User` 테이블과 `Board` 테이블의 인덱스값인 `idx`를 외래키로 걸은 테이블을 생성한 후 버튼 클릭 시 Insert 시키는 방식으로 구현할 예정  
  - 취소는 불가능하게 할 예정

- **댓글 기능**  
  - `User` 테이블과 `Board` 테이블의 인덱스값인 `idx`를 외래키로 걸되 대댓글이 있을 수 있기 때문에 자기참조의 컬럼을 생성

- **댓글 수정 기능**  
  - PUT 통신을 이용하고 로그인 Session을 이용하여 본인의 작성물에 대해서만 수정 버튼이 생기도록 구현할 예정

- **댓글 삭제 기능**  
  - 로그인 Session을 이용하여 DELETE 메서드와 `PathVariable`로 구현할 예정
