<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- jQuery CDN -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <title>회원가입</title>
    <style>
        table {
            width: 480px;
            border-collapse: collapse;
            margin: 20px auto;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        button {
            cursor: pointer;
        }
        .btn-send {
            margin-left: 10px;
        }
        .td-wrapper {
        	display:"flex";
        }
        .error-wrapper {
        	display:none;
        	color:red;
        }
    </style>
</head>
<body>
    <h2 style="text-align: center;">회원가입</h2>
    <form>
        <table border="1">
            <tr>
                <th>ID</th>
                <td>
                	<input type="text" id="userId" name="userId">
					<div class="error-wrapper" id="idError"></div>
                </td>
            </tr>
            <tr>
                <th>Email</th>
                <td class="td-wrapper">
                    <input type="email" id="userEmail" name="userEmail">
                    <button type="button" class="btn-send" onclick="sendCode()">코드 전송</button>
					<div class="error-wrapper" id="emailError"></div>
                </td>
            </tr>
            <tr>
                <th>Email Code</th>
                <td>
                	<input type="text" id="emailCode" name="emailCode">
					<div class="error-wrapper" id="emailCodeError"></div>
				</td>
            </tr>
            <tr>
                <th>Name</th>
                <td>
                	<input type="text" id="userName" name="userName">
					<div class="error-wrapper" id="nameError"></div>
               	</td>
            </tr>
            <tr>
                <th>Password</th>
                <td>
                	<input type="password" id="userPassword" name="userPassword">
					<div class="error-wrapper" id="passwordError"></div>
               	</td>
            </tr>
            <tr>
                <th>Verify Password</th>
                <td>
               		<input type="password" id="userVerifyPassword" name="userVerifyPassword">
					<div class="error-wrapper" id="verifyPasswordError"></div>
                </td>
            </tr>
        </table>
        <div style="text-align: center; margin-top: 10px;">
            <button type="submit">회원가입</button>
        </div>
    </form>

    <script>
    let responseEmailCode = null;
    let isGood = false;
    
    document.querySelector("form").addEventListener("submit", async function(event) {
        event.preventDefault(); // 기본 폼 제출 방지
		console.log("????");
        const formData = {
            userId: document.getElementById("userId").value,
            userEmail: document.getElementById("userEmail").value,
            emailCode: document.getElementById("emailCode").value,
            userName: document.getElementById("userName").value,
            userPassword: document.getElementById("userPassword").value,
            userVerifyPassword: document.getElementById("userVerifyPassword").value
        };

        const response = await fetch("/api/v1/member/join", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(formData)
        });

        if (response.ok) {
            alert("회원가입 성공!");
            window.location.href="/api/v1/";
        } else {
            alert("회원가입 실패!");
        }
    });
    
    $(function () {
        const $userId = $("#userId"),
              $userEmail = $("#userEmail"),
              $emailCode = $("#emailCode"),
              $userName = $("#userName"),
              $userPassword = $("#userPassword"),
              $userVerifyPassword = $("#userVerifyPassword");
        
        function displayError(id, message) {
            const errorElement = document.getElementById(id);
            errorElement.style.display = "block";
            errorElement.innerText = message;
        }

        function blurEventHandler($input, errorId) {
            $input.blur(function() {
                const value = $input.val();
                const fieldName = $input.attr('name');
                
                // 입력 여부 확인
                if (value.length < 1) {
                    const messages = {
                        userId: '아이디를 입력해주세요.',
                        userEmail: '이메일을 입력해주세요.',
                        emailCode: '인증번호를 입력해주세요.',
                        userName: '닉네임을 입력해주세요.',
                        userPassword: '비밀번호를 입력해주세요.',
                        userVerifyPassword: '비밀번호를 한번 더 입력해주세요.'
                    };
                    displayError(errorId, messages[fieldName]);
                    return;
                }

                // 양식 유효성 검사
                const validations = {
                    userId: /^[a-zA-Z0-9]+$/,
                    emailCode: /^[0-9]+$/,
                    userEmail: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{1,}$/
                };

                if (validations[fieldName] && !validations[fieldName].test(value)) {
                    const messages = {
                        userId: '영어, 숫자로 4글자 이상 입력해주세요.',
                        emailCode: '숫자만 입력 가능합니다.',
                        userEmail: '유효한 이메일 형식을 입력해주세요.'
                    };
                    displayError(errorId, messages[fieldName]);
                    return;
                }

                // 최소 입력 값 확인
                const minLengthMessages = {
                    userId: '영어, 숫자로 4글자 이상 입력해주세요.',
                    userPassword: '4글자 이상의 비밀번호만 사용 가능합니다.'
                };
                if (minLengthMessages[fieldName] && value.length <= 3) {
                    displayError(errorId, minLengthMessages[fieldName]);
                    return;
                }
                
                if (fieldName == 'emailCode' && responseEmailCode != value) {
                    displayError(errorId, "인증번호가 다릅니다.");
                    return;
                }
                
                // 통신 구문
                if (fieldName == 'userId') {checkDuplicationId();}
                
                
            	
            });
        }

        blurEventHandler($userId, "idError");
        blurEventHandler($userEmail, "emailError");
        blurEventHandler($emailCode, "emailCodeError");
        blurEventHandler($userName, "nameError");
        blurEventHandler($userPassword, "passwordError");
        blurEventHandler($userVerifyPassword, "verifyPasswordError");
    });

    async function checkDuplicationId() {
        try {
            const userId = document.getElementById("userId").value;
            const response = await fetch('/api/v1/member/check-id', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ userId })
            });
            

            if (response.ok) {
                responseEmailCode = await response.json();
                console.log(responseEmailCode);
                document.getElementById("idError").style.display = "none";
            } else {
                document.getElementById("idError").style.display = "block";
                document.getElementById("idError").innerText = (await response.json()).message;
            }
        } catch (error) {
            console.error('Error occurred while checking ID duplication:', error);
        }
    }
    
    async function sendCode() {
        try {
            const userEmail = document.getElementById("userEmail").value;
            const response = await fetch('/api/v1/member/send-code', {
                method: 'POST',
                headers: { 'Content-Type' : 'application/json' },
                body: JSON.stringify({ userEmail: userEmail })
            });
            if (response.ok) {
            	responseEmailCode = await response.text();
                console.log(responseEmailCode);
                document.getElementById("emailError").style.display = "none";
            } else {
                document.getElementById("emailError").style.display = "block";
                const errorData = await response.json();
                document.getElementById("emailError").innerText = errorData.message;
            }
        } catch (error) {
            console.error("네트워크 오류:", error);
        }
    }

    </script>
</body>
</html>