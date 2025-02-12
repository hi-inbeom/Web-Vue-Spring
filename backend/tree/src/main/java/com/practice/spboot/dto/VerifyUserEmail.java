package com.practice.spboot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter // 제거 시 userEmail의 값을 검증하지 못하고 NotBlank의 문구가 출력됨
public class VerifyUserEmail {
    @NotBlank(message = "이메일을 입력해주세요.")
	@Email(message = "올바른 이메일주소가 아닙니다")
	private String userEmail;
}
