package com.practice.spboot.service.user;

import org.springframework.stereotype.Service;

import com.practice.spboot.domain.user.Authorities;
import com.practice.spboot.domain.user.User;
import com.practice.spboot.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public Boolean findByUserId(String userId) {
		return userRepository.findByUserId(userId) != null;
	}

	public Boolean save() {
		User adminUser = new User("admin", "admin", "admin@example.com");
		adminUser.setUserAuth(Authorities.ADMIN);
		return userRepository.save(adminUser) != null;
	}
}
