package com.userService.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.userService.entity.User;
import com.userService.external.Mentor;
import com.userService.external.TechnicalCourseDto;
import com.userService.external.TrainingTracker;
import com.userService.external.TrainingTrackerDto;
import com.userService.serviceImpl.UserServiceImpl;

@RestController
@RequestMapping("/userown")
public class UserOwnController {
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
//	@Autowired
//	TechnicalCourseServiceImpl technicalCourseServiceImpl;
//	
//	@Autowired
//	TrainingTrackerServiceImpl trainingTrackerServiceImpl;
	
	@GetMapping("/welcome")	public String welcome()
	{
		return "welcome to user cotroller";
	}
	
	@GetMapping("/mentors")
    public ResponseEntity<Mentor[]> getAllMentorsByTechnicalCourseName(@RequestBody TechnicalCourseDto technicalCourseDto,@RequestHeader (name="Authorization") String token)
    {
    	System.out.println(technicalCourseDto);
    	System.out.println(token);
		RestTemplate restTemplate=new RestTemplate();
		
		Map<String, String> requestBody = new HashMap<>();
		requestBody.put("technicalCourseName", technicalCourseDto.getTechnicalCourseName());
		
		
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("Authorization","Bearer " +token);
		
		HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
		
		ResponseEntity<Mentor[]> resMentor=restTemplate.postForEntity("http://localhost:8080/technicalCourses/mentors",requestEntity,Mentor[].class);
		
		Mentor[] lmentor=resMentor.getBody();
		//List<Mentor> lmentor=(List<Mentor>) restTemplate.getForObject("http://localhost:8080/technicalCourses/mentors", Mentor.class);
    	//List<Mentor> lmentor=technicalCourseServiceImpl.getAllMentorsByTechnicalCourseName(technicalCourseDto.getTechnicalCourseName());
    	return new ResponseEntity<>(lmentor,HttpStatus.OK);
    }
	
	@PostMapping("/select")
    public ResponseEntity<TrainingTracker> saveTrainingTracker(@RequestBody TrainingTrackerDto trainingTrackerDto,@RequestHeader (name="Authorization") String token){
		System.out.println(trainingTrackerDto);
    	System.out.println(token);
		RestTemplate restTemplate=new RestTemplate();
		
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("trainingTotalDuration", trainingTrackerDto.getTrainingTotalDuration());
		requestBody.put("trainingCompletionStatus", trainingTrackerDto.getTrainingCompletionStatus());
		requestBody.put("trainingDaysLeft", trainingTrackerDto.getTrainingDaysLeft());
		requestBody.put("technicalCourseId", trainingTrackerDto.getTechnicalCourseId());
		requestBody.put("mentorId", trainingTrackerDto.getMentorId());
		
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("Authorization","Bearer " +token);
		
		HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
		
		ResponseEntity<TrainingTracker> resTrainingTracker=restTemplate.postForEntity("http://localhost:8080/trainingTrackers/save",requestEntity,TrainingTracker.class);
		//TrainingTracker newTrainingTracker=restTemplate.getForObject("http://localhost:8080/trainingTrackers/save", TrainingTracker.class);
    	//TrainingTracker newTrainingTracker=trainingTrackerServiceImpl.saveTrainingTracker(trainingTrackerDto);
		TrainingTracker newTrainingTracker=resTrainingTracker.getBody();
        return new ResponseEntity<>(newTrainingTracker, HttpStatus.OK);
    }
	
	@GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable int userId){
    	User user=userServiceImpl.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
	
	@PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUserByUserId(@RequestBody User user, @PathVariable int userId){
    	User userRes=userServiceImpl.updateUserByUserId(user, userId);
        return new ResponseEntity<>(userRes, HttpStatus.OK);
    }

    @DeleteMapping("delete/{userId}")
    public ResponseEntity<User> deleteUserByuserId(@PathVariable int userId){
    	User userRes=userServiceImpl.deleteUserByUserId(userId);
        return new ResponseEntity<>(userRes, HttpStatus.OK);
    }

}
