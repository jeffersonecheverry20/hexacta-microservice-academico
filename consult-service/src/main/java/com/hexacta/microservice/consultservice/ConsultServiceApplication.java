package com.hexacta.microservice.consultservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.hexacta.microservice.consultservice")
@SpringBootApplication
public class ConsultServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultServiceApplication.class, args);
	}

}
