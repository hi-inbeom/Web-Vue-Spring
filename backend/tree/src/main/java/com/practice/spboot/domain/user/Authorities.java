package com.practice.spboot.domain.user;

import org.springframework.security.core.GrantedAuthority;

public enum Authorities implements GrantedAuthority {
	ADMIN,
	USER;
	
    @Override
    public String getAuthority() {
        return this.toString();
    }
}
