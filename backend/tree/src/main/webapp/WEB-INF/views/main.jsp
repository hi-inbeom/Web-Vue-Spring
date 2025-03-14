<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <style>
        .member-wrapper {
            display: flex;
            gap: 10px;
        }
        .a-tag-wrapper {
            text-decoration:none;
            color: black;
            background-color: gray;
            padding: 5px 12px 5px 12px;
        }
    </style>
</head>
<body>
    <h2>기능 테스트</h2>
	<div class="member-wrapper">
		<a class="a-tag-wrapper" href="/api/v1/join.me">
			회원가입
		</a>
		<c:choose>
			<c:when test="${ empty userId }">
				<a class="a-tag-wrapper" href="/api/v1/login.me">로그인</a>
			</c:when>
			<c:otherwise>
				<div class="a-tag-wrapper" onclick="logout()" style="cursor:pointer;">로그아웃</div>
				<a class="a-tag-wrapper" href="/api/v1/update.me">정보수정</a>
			</c:otherwise>
		</c:choose>

		<a class="a-tag-wrapper" href="/api/v1/find.me"> 계정찾기 </a>
	</div>
	<br>
	<a class="a-tag-wrapper" href="">
		게시판 이동
	</a>
	
	<script>
	function logout() {
        fetch('/api/v1/member/logout', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
        }).then(response => {
            if (response.ok) {
                window.location.href = "/api/v1/"; // 로그아웃 후 리다이렉트
            }
        });
    }
	</script>
</body>
</html>