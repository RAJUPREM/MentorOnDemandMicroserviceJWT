package com.com.mentorTrainingCourseService.service;

import java.util.List;

import com.com.mentorTrainingCourseService.entity.Mentor;
import com.com.mentorTrainingCourseService.entotyDto.MentorDto;






public interface MentorService {
	
	public List<Mentor> getAllMentor();
	
	public Mentor getMentorById(int mentorId);
	
	public Mentor saveMentor(MentorDto mentorDto);
	
	public Mentor updateMentorByMentorId( Mentor mentor, int mentorId);
	
	public Mentor deleteMentorByMentorId(int mentorId);
	
	public Mentor addTechnicalCourseByTechnicalCourseIdAndMentorId(int mentorId,int technicalCourseId);


}
