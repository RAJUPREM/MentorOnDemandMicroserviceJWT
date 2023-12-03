package com.userService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<List<Mentor>> getAllMentorsByTechnicalCourseName(@RequestBody TechnicalCourseDto technicalCourseDto)
    {
		RestTemplate restTemplate=new RestTemplate();
		List<Mentor> lmentor=(List<Mentor>) restTemplate.getForObject("http://localhost:8332/technicalCourses/mentors", Mentor.class);
    	//List<Mentor> lmentor=technicalCourseServiceImpl.getAllMentorsByTechnicalCourseName(technicalCourseDto.getTechnicalCourseName());
    	return new ResponseEntity<>(lmentor,HttpStatus.OK);
    }
	
	@PostMapping("/select")
    public ResponseEntity<TrainingTracker> saveTrainingTracker(@RequestBody TrainingTrackerDto trainingTrackerDto){
		RestTemplate restTemplate=new RestTemplate();
		TrainingTracker newTrainingTracker=restTemplate.getForObject("http://localhost:8332/trainingTrackers/save", TrainingTracker.class);
    	//TrainingTracker newTrainingTracker=trainingTrackerServiceImpl.saveTrainingTracker(trainingTrackerDto);
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
