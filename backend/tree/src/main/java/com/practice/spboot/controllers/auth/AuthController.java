package com.practice.spboot.controllers.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.spboot.service.auth.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	};
	
    @GetMapping("/checkUserId")
    public Boolean checkUserId(@RequestParam String userId) {
    	boolean isAvailable = authService.isAvailableUserId(userId);
    	
    	return true;
    }
}
