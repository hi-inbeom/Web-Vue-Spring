package com.practice.spboot.domain.user;

import com.practice.spboot.domain.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

// DB와 통신되는 클래스, DB의 모든 데이터를 받는 역할
@Entity
public class User extends BaseEntity {
	
	@Column(nullable=false, unique=true)
	private String userId;
	
	@Column(nullable=false)
	private String userPassword;
	
	@Column(nullable=false)
	private String userEmail;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Authorities userAuth;
	
	public User(Long idx) {
		super(idx);
	}
	
	public User(String userId, String userPassword, String userEmail) {
		this();
		this.userId = userId;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
	}

	public User() {
		this.userAuth = Authorities.USER;
	}
	
	public void setUserAuth(Authorities userAuth) {
		this.userAuth = userAuth;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public String getUserEmail() {
		return userEmail;
	}
	
}
	
