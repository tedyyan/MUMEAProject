package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
public class SpringbootAdminEaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAdminEaApplication.class, args);
	}

}
