package com.practice.spboot.jspController;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.practice.spboot.dto.LoginRequest;
import com.practice.spboot.dto.UserDto;
import com.practice.spboot.dto.VerifyUserEmail;
import com.practice.spboot.service.SessionService;
import com.practice.spboot.service.user.UserMailService;
import com.practice.spboot.service.user.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {
	private final UserService userService;
	private final SessionService sessionService;
	private final UserMailService userMailServicel;
	
	// 사용 중인 아이디인지 확인
    @PostMapping("/check-id")
    public ResponseEntity<Boolean> authCheckId(@RequestBody Map<String, String> request) {
        String userId = request.get("userId");
    	userService.checkByUserId(userId);
    	return new ResponseEntity<>(true, HttpStatus.OK);
    }
	
	// 코드 이메일로 전송
	@PostMapping("/send-code")
	public ResponseEntity<String> sendCode(@RequestBody @Valid VerifyUserEmail verifyUserEmail,
												HttpSession session) {
		userService.duplicateCheckForUserEmail(verifyUserEmail.getUserEmail());
		String verifyCode = userMailServicel.sendVerificationEmail(verifyUserEmail.getUserEmail());
		sessionService.saveVerificationData(session, verifyCode, verifyUserEmail.getUserEmail());
	    return new ResponseEntity<>(verifyCode, HttpStatus.OK);
	}
	
	// 코드 일치 여부 확인
	@PostMapping("/code-match")
	public ResponseEntity<Boolean> codeMatch(@RequestBody String inputCode, HttpSession session) {
		userService.verifyCode(inputCode, session);
	    return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	// 회원 가입 전송
    @PostMapping("/join")
    public ResponseEntity<Boolean> signup(@RequestBody @Valid UserDto userDto) {
    	System.out.println(userDto);
	    return new ResponseEntity<>(userService.save(userDto), HttpStatus.OK);
    }
	
    
    @PostMapping("/login")
    public void userLogin(@RequestBody @Valid LoginRequest loginRequest, HttpSession session) {
    	userService.userLogin(session, loginRequest);
    	sessionService.createSession(session, loginRequest.getUserId());
    }
    
    @PostMapping("/logout")
    public String userLogout(HttpSession session) {
    	sessionService.destroySession(session);
        return "redirect:/api/v1/";
    }
}
