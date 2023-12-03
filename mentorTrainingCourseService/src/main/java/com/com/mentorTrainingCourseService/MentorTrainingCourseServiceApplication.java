package com.com.mentorTrainingCourseService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MentorTrainingCourseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MentorTrainingCourseServiceApplication.class, args);
	}

}
