package com.grs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class GrsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrsApplication.class, args);
	}

}
