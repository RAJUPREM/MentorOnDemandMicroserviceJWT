package com.com.mentorTrainingCourseService.service;

import java.util.List;

import com.com.mentorTrainingCourseService.entity.Mentor;
import com.com.mentorTrainingCourseService.entity.TechnicalCourse;
import com.com.mentorTrainingCourseService.entotyDto.TechnicalCourseDto;


public interface TechnicalCourseService {
	
	public List<TechnicalCourse> getAllTechnicalCourse();
	
	public TechnicalCourse getTechnicalCourseById(int technicalCourseId);
	
	public TechnicalCourse saveTechnicalCourse(TechnicalCourseDto technicalCourseDto);
	
	public TechnicalCourse updateTechnicalCourseByTechnicalCourseId( TechnicalCourse technicalCourse, int technicalCourseId);
	
	public TechnicalCourse deleteTechnicalCourseByTechnicalCourseId(int technicalCourseId);
	
	//public TechnicalCourse addMentorByMentorIdAndTechnicalCourseId(int mentorId,int technicalCourseId);
	
	public List<Mentor> getAllMentorsByTechnicalCourseName(String technicalCourseName);


}
