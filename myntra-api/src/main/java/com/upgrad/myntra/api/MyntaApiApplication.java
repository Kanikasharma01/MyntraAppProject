package com.upgrad.myntra.api;

import com.upgrad.myntra.service.ServiceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ServiceConfiguration.class)
public class MyntaApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(MyntaApiApplication.class, args);
	}
}
