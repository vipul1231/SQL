package com.example.perf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example")
public class RiskExecutorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RiskExecutorApplication.class, args);
	}

}
