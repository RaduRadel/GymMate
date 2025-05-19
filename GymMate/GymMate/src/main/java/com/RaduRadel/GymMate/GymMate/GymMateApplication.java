package com.RaduRadel.GymMate.GymMate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.RaduRadel.GymMate.GymMate.repo.UserRepository;
import com.RaduRadel.GymMate.GymMate.service.RegistrationService;

@SpringBootApplication
public class GymMateApplication {
	public static void main(String[] args) {
		SpringApplication.run(GymMateApplication.class, args);
	}

	@Bean
	public CommandLineRunner initAdmin(UserRepository userRepo,
									   RegistrationService regSvc) {
		return args -> {
			if (userRepo.findByUsername("admin").isEmpty()) {
				regSvc.register("admin", "admin", "ADMIN");
				System.out.println("✔ Default admin/admin account created.");
			} else {
				System.out.println("ℹ️  Admin account already exists.");
			}
		};
	}
}
