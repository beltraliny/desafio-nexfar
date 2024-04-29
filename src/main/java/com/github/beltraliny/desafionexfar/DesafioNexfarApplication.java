package com.github.beltraliny.desafionexfar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class DesafioNexfarApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioNexfarApplication.class, args);
	}

}
