package com.com.mentorTrainingCourseService.service;

import java.util.List;

import com.com.mentorTrainingCourseService.entity.MentorHistory;
import com.com.mentorTrainingCourseService.entotyDto.MentorDto;



public interface MentorHistoryService {
	
	public List<MentorHistory> getAllMentorHistory();
	
	public MentorHistory getMentorHistoryById(int mentorHistoryId);
	
	public MentorHistory saveMentorHistory(MentorDto mentorDto);
	
	public MentorHistory updateMentorHistoryByMentorHistoryId( MentorHistory mentorHistory, int mentorHistoryId);
	
	public MentorHistory deleteMentorHistoryByMentorHistoryId(int mentorHistoryId);

}
