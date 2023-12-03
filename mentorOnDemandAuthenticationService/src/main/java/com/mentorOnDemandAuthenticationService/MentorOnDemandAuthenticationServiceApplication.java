package com.mentorOnDemandAuthenticationService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MentorOnDemandAuthenticationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MentorOnDemandAuthenticationServiceApplication.class, args);
	}

}
