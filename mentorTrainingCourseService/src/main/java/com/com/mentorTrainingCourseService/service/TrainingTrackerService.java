package com.com.mentorTrainingCourseService.service;



import java.util.List;

import com.com.mentorTrainingCourseService.entity.TrainingTracker;
import com.com.mentorTrainingCourseService.entotyDto.TrainingTrackerDto;

public interface TrainingTrackerService {
	
	
	public List<TrainingTracker> getAllTrainingTracker();
	
	public TrainingTracker getTrainingTrackerById(int trainingTrackerId);
	
	public TrainingTracker saveTrainingTracker(TrainingTrackerDto trainingTrackerDto);
	
	public TrainingTracker updateTrainingTrackerByTrainingTrackerId( TrainingTracker trainingTracker, int trainingTrackerId);
	
	public TrainingTracker deleteTrainingTrackerByTrainingTrackerId(int trainingTrackerId);

}
