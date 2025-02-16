package com.practice.spboot.service.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
	private final UserSessionService userSessionService;

	public void checkByUserId(String userId) {
		// 있을 경우 true, 없을 경우 false
	    if (userRepository.findByUserId(userId) != null) {
	        throw new UserExceptions("userId", "userId", "사용 중인 아이디입니다.");
	    }
	}
	
	public void checkByUserName(String userName) {
	    if (userRepository.findByUserName(userName) != null) {
	        throw new UserExceptions("userName", "userName", "사용 중인 닉네임입니다.");
	    }
	}

	public Boolean save(UserDto userDto) {
		return userRepository.save(userDto.toEntity()) != null;
	}
	
	@Transactional
	public void update(UserDto userDto) {
	    String moddateStr = userDto.getModdate();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
	    LocalDateTime moddate = LocalDateTime.parse(moddateStr, formatter);
		
		userRepository.update(userDto.getUserName(), userDto.getUserPassword(), userDto.getUserId(), moddate);
	}

	public UserDto findByUserEmail(String userEmail) {
		return UserDto.of(userRepository.findByUserEmail(userEmail));
	}
	
	private void duplicateCheckForUserId(UserDto dto){
		if (userRepository.findByUserId(dto.getUserId()) != null) {
			throw new UserExceptions("userdto", "userId", "존재하는 아이디");
		};
	}

	private void duplicateCheckForUserName(UserDto dto){
		if (userRepository.findByUserId(dto.getUserName()) != null) {
			throw new UserExceptions("userdto", "userName", "존재하는 닉네임");
		};
	}

	public void userLogin(LoginRequest loginRequest, HttpSession httpSession) {
		UserDto userDto = new UserDto();
		
		
	    try {
		    userDto = UserDto.of(userRepository.findByUserId(loginRequest.getUserId()));
	    } catch (Exception err) {
	        throw new UserExceptions("userId", "userId", "존재하지 않는 아이디입니다.");
	    }
	    
	    if (!userDto.getUserPassword().equals(loginRequest.getUserPassword())) {
	        throw new UserExceptions("userPassword", "userPassword", "비밀번호가 일치하지 않습니다.");
	    }
	    userSessionService.startSession(userDto, httpSession);
	}

	public void userLogout(HttpSession httpSession) {
		userSessionService.endSession(httpSession);
	}

}
