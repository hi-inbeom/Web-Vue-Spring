package com.practice.spboot.controllers.user;

import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.spboot.dto.LoginRequest;
import com.practice.spboot.dto.UserDto;
import com.practice.spboot.dto.VerifyUserEmail;
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
	

	@PostMapping("/sign/verify-email")
	public void verifyEmail(@RequestBody @Valid VerifyUserEmail verifyUserEmail) {	}
	
	@PostMapping("/sign/send-verify-code")
	public void sendVerifyCode() { }
	
    @PostMapping("/sign/check-id")
    public void authCheckId(@RequestBody String userId) {
    	userService.checkByUserId(userId);
    }
    
    @PostMapping("/sign/check-name")
    public void checkByUserName(@RequestBody String userName) {
        userService.checkByUserName(userName);
    }
    
    // UserDto검증 시 BaseDto의 값은 없어도 됨
    // BaseDto의 값을 넘겨줄 경우 해당 값을 사용하고 안넘겨주면 기본값이나 null이 됨.
    // @RequestBody의 유무에 따라 Content-Type이 결정됨 있을 경우 raw로 해도 body를 가져오지만 없을 경우 x-.... 이나 form-data를 사용
    @PostMapping("/sign/signup")
    public Boolean signup(@RequestBody @Valid UserDto userDto) {
    	System.out.println(userDto.toString());
    	return userService.save(userDto);
    }
    
    @GetMapping("/sign/find")
    public UserDto userFind(@RequestParam("userEmail") String userEmail) {
    	return userService.findByUserEmail(userEmail);
    }
    
    @PostMapping("/login")
    public void userLogin(@RequestBody @Valid LoginRequest loginRequest, HttpServletRequest req) {
    	// HttpServletRequest 사용자의 요청에 대한 정보를 담음
    	// getSession 요청에 세션을 가져오고 없을 경우 새로운 세션이 만들어짐, 만들어진 세션 ID를 쿠키형태로 클라이언트에 전송
    	// HttpSession를 통해 서버에서 세션 컨트롤을 함
    	HttpSession httpSession = req.getSession(true); // 기본값은 true
    	userService.userLogin(loginRequest, httpSession);
    }
    
    @PostMapping("/logout")
    public void userLogout(HttpServletRequest req) {
    	// HttpServletRequest RequestBody로 바인딩 못함 이미 완성된 자료형
    	HttpSession httpSession = req.getSession(false);
    	userService.userLogout(httpSession);
    }
    
    @PostMapping("/sessionTest")
    public void sessionTest(HttpServletRequest req) {
    	HttpSession httpSession = req.getSession(false);
        if (httpSession != null) {
            // 세션에 저장된 모든 속성 이름을 가져옵니다
            Enumeration<String> attributeNames = httpSession.getAttributeNames();
            
            // 속성 이름과 값을 출력합니다
            while (attributeNames.hasMoreElements()) {
                String attributeName = attributeNames.nextElement();
                Object attributeValue = httpSession.getAttribute(attributeName);
                System.out.println(attributeName + " = " + attributeValue);
            }
        } else {
            System.out.println("세션이 존재하지 않습니다.");
        }
    }
}
