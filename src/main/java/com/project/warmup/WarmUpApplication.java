package com.project.warmup;

import com.project.warmup.Services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WarmUpApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarmUpApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.save("admin","12345","admin");
		};
	}

}
