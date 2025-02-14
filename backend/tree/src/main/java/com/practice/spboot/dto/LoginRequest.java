package com.practice.spboot.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
	@NotBlank(message="아이디를 입력해주세요.")
	private String userId;
	
	@NotBlank(message="비밀번호를 입력해주세요.")
	private String userPassword;
}
