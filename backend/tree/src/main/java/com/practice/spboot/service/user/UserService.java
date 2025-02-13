package com.practice.spboot.service.user;

import org.springframework.stereotype.Service;

import com.practice.spboot.domain.user.User;
import com.practice.spboot.domain.user.UserRepository;
import com.practice.spboot.dto.UserDto;
import com.practice.spboot.exception.UserExceptions;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final UserSessionService userSessionService;

	public Boolean findByUserId(String userId) {
		System.out.println("????");
		// 있을 경우 true, 없을 경우 false
		return userRepository.findByUserId(userId) != null;
	}
	
	public Boolean findByUserName(String userName) {
		return userRepository.findByUserName(userName) != null;
	}

	public Boolean save(UserDto userDto) {
		return userRepository.save(userDto.toEntity()) != null;
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

	public void userLogin(HttpSession httpSession, String userId, String userPassword) {
	    UserDto userDto = UserDto.of(userRepository.findByUserId(userId));
	    
	    if (userDto == null) {
	        throw new UserExceptions("userId", "userId", "존재하지 않는 아이디입니다.");
	    }
	    
	    if (!userDto.getUserPassword().equals(userPassword)) {
	        throw new UserExceptions("userPassword", "userPassword", "비밀번호가 일치하지 않습니다.");
	    }
	    
	    userSessionService.startSession(httpSession, userDto);
	}

	public void userLogout(HttpSession httpSession) {
		userSessionService.endSession(httpSession);
	}

}
