# Web-Vue-Spring

## 프로젝트 소개
일부 기능은 JSP 기반 SSR을 활용하여 개발하고
전체적인 아키텍처는 SPA를 기반으로 MVC 디자인 패턴을 적용해 구현 중인 웹사이트.

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

## 코드 구현 방식
![image](https://github.com/hi-inbeom/Web-Vue-Spring/blob/main/readme-images/Controller.png)  
> Presentation Layer인 Controller에서는 역할에 맞도록 API와 응답에 대한 코드만 구현하였고,  
> API 구현할 때는 RestFul API를 준수하여 구현하였습니다.

![image](https://github.com/hi-inbeom/Web-Vue-Spring/blob/main/readme-images/Service.png)  
> Service에서는 ServiceImpl을 제외하여 코드의 복잡성을 낮췄고,  
> Guard Clause방식을 활용해 조건문 중첩을 줄여 가독성을 개선했습니다.

![image](https://github.com/hi-inbeom/Web-Vue-Spring/blob/main/readme-images/Repository.webp)  
![image](https://github.com/hi-inbeom/Web-Vue-Spring/blob/main/readme-images/DAO.webp)  
![image](https://github.com/hi-inbeom/Web-Vue-Spring/blob/main/readme-images/DTO.webp)  
> DAO에서 Base 클래스를 사용하여 DB 테이블의 공통 컬럼(인덱스, 등록일, 수정일)을 추상화하여 중복 코드를 줄이고,  
> User 및 Board 엔티티에서 이를 상속받도록 하여 코드의 재사용성을 높혔습니다.  
> DTO에서도 BaseDto 클래스를 사용하여 중복 코드를 줄이고 DAO와 동일한 구조로 일관성을 유지하였습니다.
