package com.practice.spboot.service.user;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.practice.spboot.dto.UserDto;

import jakarta.servlet.http.HttpSession;

@Service
public class UserSessionService {
	
    public String getAuthUsername(HttpSession httpSession) {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    
    public void startSession(HttpSession httpSession, UserDto userDto) {
    	httpSession.setAttribute("userDto", userDto);
    	httpSession.setMaxInactiveInterval(1800); // 30분
    }
    
    public void endSession(HttpSession httpSession) {
    	httpSession.invalidate();
    }

}
