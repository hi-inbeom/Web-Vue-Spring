package com.practice.spboot.controllers.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.spboot.dto.LoginRequest;
import com.practice.spboot.dto.UserDto;
import com.practice.spboot.dto.VerifyUserEmail;
import com.practice.spboot.exception.UserExceptions;
import com.practice.spboot.service.SessionService;
import com.practice.spboot.service.user.UserMailService;
import com.practice.spboot.service.user.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor // Bean 개념과 스프링 컨테이너 개념이 베이스로 생성자 주입 방식의 의존성 주입 어노테이션
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private final UserService userService;
	private final SessionService sessionService;
	private final UserMailService userMailServicel;
	//	회원 가입 중 이메일 검증
	@PostMapping("/sign/send-verify-code")
	public ResponseEntity<String> verifyEmail(@RequestBody @Valid VerifyUserEmail verifyUserEmail,
												HttpSession session) {
		userService.duplicateCheckForUserEmail(verifyUserEmail.getUserEmail());
		String verifyCode = userMailServicel.sendVerificationEmail(verifyUserEmail.getUserEmail());
		sessionService.saveVerificationData(session, verifyCode, verifyUserEmail.getUserEmail());
	    return new ResponseEntity<>(verifyCode, HttpStatus.OK);
	}
	
    @PatchMapping("/update")
    public void userUpdate(@RequestBody UserDto userDto) {
    	userService.update(userDto);
    }
	
	
	// 코드 일치 여부 확인
	@PostMapping("/sign/verify-code")
	public ResponseEntity<Boolean> verifyCode(@RequestBody String inputCode, HttpSession session) {
		userService.verifyCode(inputCode, session);
	    return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	// 사용 중인 이메일인지 확인
    @PostMapping("/sign/check-email")
    public ResponseEntity<String> authCheckEmail(@RequestBody @Valid VerifyUserEmail verifyUserEmail, HttpSession session) {
    	userService.checkForUserEmail(verifyUserEmail.getUserEmail());
		String verifyCode = userMailServicel.sendVerificationEmail(verifyUserEmail.getUserEmail());
		sessionService.saveVerificationData(session, verifyCode, verifyUserEmail.getUserEmail());
	    return new ResponseEntity<>(verifyCode, HttpStatus.OK);
    }
	
	// 사용 중인 아이디인지 확인
    @PostMapping("/sign/check-id")
    public void authCheckId(@RequestBody String userId) {
    	userService.checkByUserId(userId);
    }
    
    // 사용 중인 닉네임인지 확인
    @PostMapping("/sign/check-name")
    public void checkByUserName(@RequestBody String userName) {
        userService.checkByUserName(userName);
    }
    
    // UserDto검증 시 BaseDto의 값은 없어도 됨
    // BaseDto의 값을 넘겨줄 경우 해당 값을 사용하고 안넘겨주면 기본값이나 null이 됨.
    // @RequestBody의 유무에 따라 Content-Type이 결정됨 있을 경우 raw로 해도 body를 가져오지만 없을 경우 x-.... 이나 form-data를 사용
    @PostMapping("/sign/signup")
    public Boolean signup(@RequestBody @Valid UserDto userDto) {
    	return userService.save(userDto);
    }
    
    @PostMapping("/find")
    public UserDto userFind(@RequestBody @Valid VerifyUserEmail verifyUserEmail) {
    	return userService.findByUserEmail(verifyUserEmail.getUserEmail());
    }
    

    
    @PostMapping("/login")
    public void userLogin(@RequestBody @Valid LoginRequest loginRequest, HttpSession session) {
    	userService.userLogin(session, loginRequest);
    	sessionService.createSession(session, loginRequest.getUserId());
    }
    
    @PostMapping("/logout")
    public void userLogout(HttpSession session) {
    	sessionService.destroySession(session);
    }
    
    @PostMapping("/check-login")
    public ResponseEntity<Boolean> checkLogin(HttpSession session) {
    	return new ResponseEntity<>(sessionService.checkLogin(session), HttpStatus.OK);
    }
    
}
