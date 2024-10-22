package com.example.boot11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value="classpath:custom.properties")
@SpringBootApplication
public class Boot15JspApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot15JspApplication.class, args);
	}

}
