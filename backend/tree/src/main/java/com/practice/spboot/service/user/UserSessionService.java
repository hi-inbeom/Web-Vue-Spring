package com.practice.spboot.service.user;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.practice.spboot.domain.user.User;

@Service
public class UserSessionService {

    public String getAuthUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
