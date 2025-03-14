<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>
    <form>
        <label for="userId">아이디:</label>
        <input type="text" id="userId" name="userId" required><br><br>
        
        <label for="userPassword">비밀번호:</label>
        <input type="password" id="userPassword" name="userPassword" required><br><br>

        <button type="submit">로그인</button>
    </form>
    
    <script>
    document.querySelector("form").addEventListener("submit", async function(event) {
        event.preventDefault(); // 기본 폼 제출 방지
        const formData = {
            userId: document.getElementById("userId").value,
            userPassword: document.getElementById("userPassword").value
        };

        const response = await fetch("/api/v1/member/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(formData)
        });

        window.location.href="/api/v1/";
    });
    </script>
</body>
</html>
