package com.assignment.Social_Guard_Api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SocialGuardApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialGuardApiApplication.class, args);
	}

}
