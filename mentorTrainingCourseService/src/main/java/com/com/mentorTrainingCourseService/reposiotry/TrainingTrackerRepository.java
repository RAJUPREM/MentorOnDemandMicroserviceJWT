package com.com.mentorTrainingCourseService.reposiotry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.com.mentorTrainingCourseService.entity.TrainingTracker;

@Repository
public interface TrainingTrackerRepository extends JpaRepository<TrainingTracker,Integer>{

}
