package com.practice.spboot.dto;

import com.practice.spboot.domain.user.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//view와 통신하기 위한 클래스, 중요한 데이터를 노출시키지 않기 위한 클래스 (column의 idx값 같은 것들)
public class UserDto extends BaseDto {
	@Size(min = 4, message = "아이디는 4글자 이상 입력해주세요")
    private String userId;

    @Size(min = 4, message = "비밀번호는 4글자 이상 입력해주세요")
    private String userPassword;
    
    @NotBlank(message = "이메일 주소를 입력해주세요")
    @Email(message = "올바른 이메일주소가 아닙니다")
    private String userEmail;
    
    public UserDto() {
    }
    
    private UserDto(User user) {
    	super(user);
    	this.userId = user.getUserId();
    	this.userPassword = user.getUserPassword();
    	this.userEmail = user.getUserEmail();
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
    
    public User toEntity() {
        return new User(userId, userPassword, userEmail);
    }
    
    public static UserDto of(User user) {
    	return new UserDto(user);
    }
}
