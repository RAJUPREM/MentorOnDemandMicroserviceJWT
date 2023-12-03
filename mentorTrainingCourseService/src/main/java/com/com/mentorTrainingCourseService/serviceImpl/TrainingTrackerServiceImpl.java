package com.com.mentorTrainingCourseService.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.com.mentorTrainingCourseService.entity.Mentor;
import com.com.mentorTrainingCourseService.entity.TechnicalCourse;
import com.com.mentorTrainingCourseService.entity.TrainingTracker;
import com.com.mentorTrainingCourseService.entotyDto.TrainingTrackerDto;
import com.com.mentorTrainingCourseService.reposiotry.MentorRepository;
import com.com.mentorTrainingCourseService.reposiotry.TechnicalCourseRepository;
import com.com.mentorTrainingCourseService.reposiotry.TrainingTrackerRepository;
import com.com.mentorTrainingCourseService.service.TrainingTrackerService;


@Service
public class TrainingTrackerServiceImpl implements TrainingTrackerService{

	@Autowired
	TrainingTrackerRepository trainingTrackerRepository;
	
	@Autowired
	MentorRepository mentorRepository;
	
	@Autowired
	TechnicalCourseRepository technicalCourseRepository;
	
	@Override
	public List<TrainingTracker> getAllTrainingTracker() {
		List<TrainingTracker> ltrainingtrackers=trainingTrackerRepository.findAll();
		return ltrainingtrackers;
	}

	@Override
	public TrainingTracker getTrainingTrackerById(int trainingTrackerId) {
		Optional<TrainingTracker> tempTrainingTracker=trainingTrackerRepository.findById(trainingTrackerId);
		return tempTrainingTracker.get();
	}

	@Override
	public TrainingTracker saveTrainingTracker(TrainingTrackerDto trainingTrackerDto) {
		Optional<Mentor> tempMentor=mentorRepository.findById(trainingTrackerDto.getMentorId());
		Mentor mentor=tempMentor.get();
		
		Optional<TechnicalCourse> tempTechnicalCourse=technicalCourseRepository.findById(trainingTrackerDto.getTechnicalCourseId());
		TechnicalCourse technicalCourse=tempTechnicalCourse.get();
		
		TrainingTracker trainingTracker=new TrainingTracker();
		trainingTracker.setMentor(mentor);
		trainingTracker.setTechnicalCourse(technicalCourse);
		trainingTracker.setTrainingCompletionStatus(trainingTrackerDto.getTrainingCompletionStatus());
		trainingTracker.setTrainingDaysLeft(trainingTrackerDto.getTrainingDaysLeft());
		trainingTracker.setTrainingTotalDuration(trainingTrackerDto.getTrainingTotalDuration());
		
		trainingTrackerRepository.save(trainingTracker);
		return trainingTracker;
	}

	@Override
	public TrainingTracker updateTrainingTrackerByTrainingTrackerId(TrainingTracker trainingTracker,
			int trainingTrackerId) {
		Optional<TrainingTracker> tempTrainingTracker=trainingTrackerRepository.findById(trainingTrackerId);
		TrainingTracker newTrainingTracker=tempTrainingTracker.get();
		newTrainingTracker.setMentor(trainingTracker.getMentor());
		newTrainingTracker.setTrainingCompletionStatus(trainingTracker.getTrainingCompletionStatus());
		newTrainingTracker.setTechnicalCourse(trainingTracker.getTechnicalCourse());
		newTrainingTracker.setTrainingDaysLeft(trainingTracker.getTrainingDaysLeft());
		newTrainingTracker.setTrainingTotalDuration(trainingTracker.getTrainingTotalDuration());
		trainingTrackerRepository.save(newTrainingTracker);
		return newTrainingTracker;
	}

	@Override
	public TrainingTracker deleteTrainingTrackerByTrainingTrackerId(int trainingTrackerId) {
		trainingTrackerRepository.deleteById(trainingTrackerId);
		return null;
	}

}
