package com.practice.spboot.controllers.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.spboot.dto.UserDto;
import com.practice.spboot.dto.VerifyUserEmail;
import com.practice.spboot.service.user.JwtUserService;
import com.practice.spboot.service.user.UserService;
import com.practice.spboot.service.user.UserSessionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor // Bean 개념과 스프링 컨테이너 개념이 베이스로 생성자 주입 방식의 의존성 주입 어노테이션
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private final UserService userService;
	private final UserSessionService userSessionService;
//	private final JwtUserService jwtUserService;
	

	@PostMapping("/sign/verify-email")
	public void verifyEmail(@RequestBody @Valid VerifyUserEmail verifyUserEmail) {	}
	
	@PostMapping("/sign/send-verify-code")
	public void sendVerifyCode() { }
	
    @PostMapping("/sign/check-id")
    public Boolean authCheckId(@RequestBody String userId) {
    	System.out.println(userId);
        return userService.findByUserId(userId);
    }
    
    @PostMapping("/sign/check-name")
    public Boolean authCheckName(@RequestBody String userName) {
        return userService.findByUserName(userName);
    }
    
    // UserDto검증 시 BaseDto의 값은 없어도 됨
    // BaseDto의 값을 넘겨줄 경우 해당 값을 사용하고 안넘겨주면 기본값이나 null이 됨.
    // @RequestBody의 유무에 따라 Content-Type이 결정됨 있을 경우 raw로 해도 body를 가져오지만 없을 경우 x-.... 이나 form-data를 사용
    @PostMapping("/sign/signup")
    public Boolean signup(@RequestBody @Valid UserDto userDto) {
    	return userService.save(userDto);
    }
    
    @GetMapping("/auth/find")
    public UserDto userFind(@RequestParam("userEmail") String userEmail) {
    	return userService.findByUserEmail(userEmail);
    }
}
