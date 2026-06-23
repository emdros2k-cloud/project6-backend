package com.aivle12.book_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.aivle12.book_backend.domain.Profile;
import com.aivle12.book_backend.domain.User;
import com.aivle12.book_backend.repository.ProfileRepository;
import com.aivle12.book_backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookBackendApplication.class, args);
	}

}
