package com.jetstream.learningmanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LmsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsServiceApplication.class, args);
	}

}
