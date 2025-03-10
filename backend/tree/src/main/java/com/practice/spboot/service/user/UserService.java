package com.practice.spboot.service.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.practice.spboot.domain.user.UserRepository;
import com.practice.spboot.dto.LoginRequest;
import com.practice.spboot.dto.UserDto;
import com.practice.spboot.exception.UserExceptions;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
	public void checkByUserId(String userId) {
		if (userId == null) {
			throw new UserExceptions("아이디를 입력해주세요.");
		}
		if (userId.length()<=3) {
			throw new UserExceptions("아이디는 4글자 이상이어야 합니다.");
		}
		// mysql에서는 대소문자 구분이 안되기 떄문에 equals로 대소문자 구분
	    if (userRepository.findByUserId(userId) != null && 
	        userRepository.findByUserId(userId).getUserId().equals(userId)) {
	        throw new UserExceptions("사용 중인 아이디입니다.");
        }
	}
	public UserDto findByUserEmail(String userEmail) {
		if (userEmail==null) {
			throw new UserExceptions("이메일을 입력해주세요");
		}
		return UserDto.of(userRepository.findByUserEmail(userEmail));
	}
	
	// 비밀번호 암호화
	private UserDto EncodePassword(UserDto userDto) {
        String encodedPassword = passwordEncoder.encode(userDto.getUserPassword());
        userDto.setUserPassword(encodedPassword);
        return userDto;
	}
	
	public Boolean save(UserDto userDto) {
		userDto = EncodePassword(userDto);
		return userRepository.save(userDto.toEntity()) != null;
	}
	
	@Transactional
	public void update(UserDto userDto) {
		userDto = EncodePassword(userDto);
	    String moddateStr = userDto.getModdate();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
	    LocalDateTime moddate = LocalDateTime.parse(moddateStr, formatter);
		
		userRepository.update(userDto.getUserName(), userDto.getUserPassword(), userDto.getUserId(), moddate);
	}

	public UserDto userLogin(HttpSession httpSession, LoginRequest loginRequest) {
	    UserDto userDto;
	    try {
	        userDto = UserDto.of(userRepository.findByUserId(loginRequest.getUserId()));
	    } catch (Exception err) {
	        throw new UserExceptions("존재하지 않는 아이디입니다.");
	    }

	    if (!passwordEncoder.matches(loginRequest.getUserPassword(), userDto.getUserPassword())) {
	        throw new UserExceptions("비밀번호가 일치하지 않습니다.");
	    }

	    return userDto;
	}
	
	public void duplicateCheckForUserEmail(String userEmail){
		if (userRepository.findByUserEmail(userEmail) != null) {
			throw new UserExceptions("가입된 이메일입니다.");
		};
	}
	
	public void checkForUserEmail(String userEmail){
		if (userRepository.findByUserEmail(userEmail) == null) {
			throw new UserExceptions("가입되어 있지 않은 이메일입니다.");
		};
	}
	
	public void checkByUserName(String userName) {
    	if (userName==null) {
    		throw new UserExceptions("닉네임을 입력해주세요");
		}
	}
	
	private void duplicateCheckForUserId(String userId){
		if (userRepository.findByUserId(userId) != null) {
			throw new UserExceptions("존재하는 아이디");
		};
	}

	private void duplicateCheckForUserName(UserDto dto){
		if (userRepository.findByUserId(dto.getUserName()) != null) {
			throw new UserExceptions("존재하는 닉네임");
		};
	}

	public void verifyCode(String inputCode, HttpSession session) {
		String storedCode = (String) session.getAttribute("verifyCode");
		if (inputCode == null) {
			throw new UserExceptions("코드를 입력해주세요.");
		}
		if (!storedCode.equals(inputCode)) {
			throw new UserExceptions("코드가 일치하지 않습니다.");
		}
	}

}
