package com.practice.spboot.service;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
public class SessionService {
	// 이메일 검증 코드와 해당 이메일 저장
    public void saveVerificationData(HttpSession session, String verifyCode, String userEmail) {
        session.setAttribute("verifyCode", verifyCode);
        session.setAttribute("userEmail", userEmail);
    }
    
    // 로그인 시 세션에 사용자 정보 저장
    public void createSession(HttpSession session, String userId) {
        // 세션에 사용자 정보 설정 (예: 로그인된 사용자 정보)
        session.setAttribute("userId", userId);
        session.setAttribute("loggedIn", true);
    }

    // 로그아웃 시 세션 정보 삭제
    public void destroySession(HttpSession session) {
        session.invalidate();  // 세션을 무효화하여 모든 정보 삭제
    }

	public Boolean checkLogin(HttpSession session) {
		return session.getAttribute("userEmail") != null;
	}
}
