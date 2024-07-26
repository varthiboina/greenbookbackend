package com.example.greenbookbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class GreenbookbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreenbookbackendApplication.class, args);
	}

}
