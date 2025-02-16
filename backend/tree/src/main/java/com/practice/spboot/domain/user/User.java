package com.practice.spboot.domain.user;

import com.practice.spboot.domain.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

// DB와 통신되는 클래스, DB의 모든 데이터를 받는 역할
@Entity
@Setter
@Getter
public class User extends BaseEntity {
	@Column(nullable=false, unique=true)
	private String userId;
	
	@Column(nullable=false)
	private String userPassword;
	
	@Column(nullable=false)
	private String userName;
	
	@Column(nullable=false)
	private String userEmail;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Authorities userAuth;
	
	public User(Long idx) {
		super(idx);
	}
	
	public User(String userId, String userPassword, String userEmail, String userName) {
		this();
		this.userId = userId;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.userName = userName;
	}

	public User() {
		this.userAuth = Authorities.USER;
	}


}
	
