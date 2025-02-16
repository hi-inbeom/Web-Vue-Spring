package com.practice.spboot.domain.user;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(String userId);

	User findByUserEmail(String userEmail);

	User findByUserName(String userName);

	@Modifying
	@Query("UPDATE User u SET u.userName = :userName, u.userPassword = :userPassword, u.moddate = :moddate WHERE u.userId = :userId")
	void update(@Param("userName") String userName,
	            @Param("userPassword") String userPassword,
	            @Param("userId") String userId,
	            @Param("moddate") LocalDateTime moddate);
}
