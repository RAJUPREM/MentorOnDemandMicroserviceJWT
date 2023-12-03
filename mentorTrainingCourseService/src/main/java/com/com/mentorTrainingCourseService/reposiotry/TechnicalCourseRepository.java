package com.com.mentorTrainingCourseService.reposiotry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.com.mentorTrainingCourseService.entity.TechnicalCourse;


@Repository
public interface TechnicalCourseRepository extends JpaRepository<TechnicalCourse,Integer>{

}
