package com.logsdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogsDemoApplication.class, args);
		System.out.println("Application started...");
	}

}
