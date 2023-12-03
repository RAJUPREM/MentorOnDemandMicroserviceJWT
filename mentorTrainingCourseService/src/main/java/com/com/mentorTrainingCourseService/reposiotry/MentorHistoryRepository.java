package com.com.mentorTrainingCourseService.reposiotry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.com.mentorTrainingCourseService.entity.MentorHistory;


@Repository
public interface MentorHistoryRepository extends JpaRepository<MentorHistory,Integer>{

}
