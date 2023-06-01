package com.sad.g15.webservicegamesrepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class WebServiceGamesRepositoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebServiceGamesRepositoryApplication.class, args);
	}

}
