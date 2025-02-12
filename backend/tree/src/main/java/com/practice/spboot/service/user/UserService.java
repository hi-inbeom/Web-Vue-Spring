package com.practice.spboot.service.user;

import org.springframework.stereotype.Service;

import com.practice.spboot.domain.user.User;
import com.practice.spboot.domain.user.UserRepository;
import com.practice.spboot.dto.UserDto;
import com.practice.spboot.exception.UserExceptions;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

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
		UserDto userDto = UserDto.of(userRepository.findByUserEmail(userEmail));
		return userDto;
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


}
