package com.example.boot14;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


@PropertySource(value="classpath:custom.properties")
@SpringBootApplication
public class Boot14ReactApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot14ReactApiApplication.class, args);
	}

}
