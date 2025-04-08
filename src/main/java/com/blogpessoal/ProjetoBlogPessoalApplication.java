package com.blogpessoal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProjetoBlogPessoalApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProjetoBlogPessoalApplication.class, args);
	}
}