package com.practice.spboot.service.user;

import org.springframework.stereotype.Service;

import com.practice.spboot.domain.user.User;
import com.practice.spboot.domain.user.UserRepository;
import com.practice.spboot.dto.UserDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public Boolean findByUserId(String userId) {
		// 있을 경우 true, 없을 경우 false
		return userRepository.findByUserId(userId) != null;
	}

	public Boolean save(UserDto userDto) {
		return userRepository.save(userDto.toEntity()) != null;
	}

	public UserDto findByuserEmail(String userEmail) {
		User user = userRepository.findByUserEmail(userEmail);
		UserDto userDto = UserDto.of(user);
		return userDto;
	}
}
