package com.com.mentorTrainingCourseService.controller;



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

import com.com.mentorTrainingCourseService.entity.Mentor;
import com.com.mentorTrainingCourseService.entotyDto.MentorDto;
import com.com.mentorTrainingCourseService.entotyDto.TechnicalCourseMentorDto;
import com.com.mentorTrainingCourseService.serviceImpl.MentorServiceImpl;






@RestController
@RequestMapping("/mentors")
public class MentorController {
	
	@Autowired
	MentorServiceImpl mentorServiceImpl;
	
	@GetMapping("/welcome")
	public String welcome()
	{
		return "welcome to mentor cotroller";
	}

	@GetMapping("/")
    public ResponseEntity<List<Mentor>> getAllMentors(){
        List<Mentor> Mentors = mentorServiceImpl.getAllMentor();
        return new ResponseEntity<>(Mentors, HttpStatus.OK);
    }

    @GetMapping("/{MentorId}")
    public ResponseEntity<Mentor> getMentorById(@PathVariable int MentorId){
    	Mentor Mentor=mentorServiceImpl.getMentorById(MentorId);
        return new ResponseEntity<>(Mentor, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Mentor> saveMentor(@RequestBody MentorDto mentorDto){
    	Mentor newMentor=mentorServiceImpl.saveMentor(mentorDto);
        return new ResponseEntity<>(newMentor, HttpStatus.OK);
    }

    @PutMapping("/update/{MentorId}")
    public ResponseEntity<Mentor> updateMentorByMentorId(@RequestBody Mentor Mentor, @PathVariable int MentorId){
    	Mentor useRes=mentorServiceImpl.updateMentorByMentorId(Mentor, MentorId);
        return new ResponseEntity<>(useRes, HttpStatus.OK);
    }

    @DeleteMapping("delete/{MentorId}")
    public ResponseEntity<Mentor> deleteMentorByMentorId(@PathVariable int MentorId){
    	Mentor newMentor=mentorServiceImpl.deleteMentorByMentorId(MentorId);
        return new ResponseEntity<>(newMentor, HttpStatus.OK);
    }
    
    @PostMapping("/addTechnicalCourse")
    public ResponseEntity<Mentor> addTechnicalCourseByTechnicalCourseIdAndMentorId(@RequestBody TechnicalCourseMentorDto  technicalCourseMentorDto) {
    	Mentor mentor=mentorServiceImpl.addTechnicalCourseByTechnicalCourseIdAndMentorId(technicalCourseMentorDto.getMentorId(), technicalCourseMentorDto.getTechnicalCourseId());
    	return new ResponseEntity<>(mentor,HttpStatus.OK);
    }
}
