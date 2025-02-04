package com.practice.spboot.controllers.user;

import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.spboot.service.user.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor // Bean 개념과 스프링 컨테이너 개념이 베이스로 생성자 주입 방식의 의존성 주입 어노테이션
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private final UserService userService;
	

	/*
	 * auth Functions
	 */
	@GetMapping("/auth/admin")
	public Boolean createAdmin() {
		return userService.save();
	}
	
    @GetMapping("/auth/checkId")
    public Boolean authCheckId(@RequestParam("userId") String userId) {
        System.out.println("check UserController");
        return userService.findByUserId(userId);
    }
}
