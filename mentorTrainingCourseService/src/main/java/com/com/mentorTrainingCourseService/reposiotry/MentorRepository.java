package com.com.mentorTrainingCourseService.reposiotry;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.com.mentorTrainingCourseService.entity.Mentor;




@Repository
public interface MentorRepository extends JpaRepository<Mentor,Integer>{
	
	public Optional<Mentor> findByMentorName(String mentorName);

}
