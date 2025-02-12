package com.practice.spboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(String userId);

	User findByUserEmail(String userEmail);

	Object findByUserName(String userName);
}
