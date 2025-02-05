package com.practice.spboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class}) -> JpaRepository가 등록되지 않는 문제가 있는듯?
@SpringBootApplication
@EnableJpaAuditing // regdate, moddate 넣기 위해서 필요
public class TreeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TreeApplication.class, args);
	}

}
