package com.optum.poc.springdbrouting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
//@EntityScan(basePackages = "com.optum.poc.springdbrouting.model")
public class SpringDbRoutingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDbRoutingApplication.class, args);
	}

}
