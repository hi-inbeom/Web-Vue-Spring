package com.practice.spboot.dto;

import com.practice.spboot.domain.user.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//view와 통신하기 위한 클래스, 중요한 데이터를 노출시키지 않기 위한 클래스 (column의 idx값 같은 것들)
@NoArgsConstructor
@Getter
@Setter
public class UserDto extends BaseDto {
	@Size(min = 4, message = "아이디는 4글자 이상 입력해주세요")
    private String userId;

    @Size(min = 4, message = "비밀번호는 4글자 이상 입력해주세요")
    private String userPassword;
    private String userVerifyPassword;
    
    @NotBlank(message = "이메일 주소를 입력해주세요")
    @Email(message = "올바른 이메일주소가 아닙니다")
    private String userEmail;
    
    @NotBlank(message = "닉네임을 입력해주세요")
    private String userName;
    
    private UserDto(User user) {
    	super(user);
    	this.userId = user.getUserId();
    	this.userPassword = user.getUserPassword();
    	this.userEmail = user.getUserEmail();
    	this.userName = user.getUserName();
    }
    
    public static UserDto of(User user) {
    	return new UserDto(user);
    }
    
    public User toEntity() {
        return new User(userId, userPassword, userEmail, userName);
    }
}
