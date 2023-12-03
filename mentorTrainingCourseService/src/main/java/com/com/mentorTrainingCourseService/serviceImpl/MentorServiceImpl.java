package com.com.mentorTrainingCourseService.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.com.mentorTrainingCourseService.entity.Mentor;
import com.com.mentorTrainingCourseService.entity.TechnicalCourse;
import com.com.mentorTrainingCourseService.entotyDto.MentorDto;
import com.com.mentorTrainingCourseService.reposiotry.MentorRepository;
import com.com.mentorTrainingCourseService.reposiotry.TechnicalCourseRepository;
import com.com.mentorTrainingCourseService.service.MentorService;



@Service
public class MentorServiceImpl implements MentorService{

	@Autowired
	MentorRepository mentorRepository;
	
	@Autowired
	TechnicalCourseRepository technicalCourseRepository;
	
	@Override
	public List<Mentor> getAllMentor() {
		List<Mentor> lmentor=mentorRepository.findAll();
		return lmentor;
	}

	@Override
	public Mentor getMentorById(int mentorId) {
		Optional<Mentor> tempMentor=mentorRepository.findById(mentorId);
		Mentor mentor=tempMentor.get();
		return mentor;
	}

	@Override
	public Mentor saveMentor(MentorDto mentorDto) {
		Mentor mentor=new Mentor();
		mentor.setMentorName(mentorDto.getMentorName());
		mentor.setMentorExperience(mentorDto.getMentorExperience());
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("pass", mentorDto.getMentorPassword());
		RestTemplate restTemplate=new RestTemplate();
		String s=restTemplate.getForObject("http://localhost:4444/jwt/getPass/{pass}", String.class,params);
		
		mentor.setMentorPassword(s);
		mentor.setTechnicalCourses(null);
		mentorRepository.save(mentor);
		return mentor;
	}

	@Override
	public Mentor updateMentorByMentorId(Mentor mentor, int mentorId) {
		Optional<Mentor> tempMentor=mentorRepository.findById(mentorId);
		Mentor newMentor=tempMentor.get();
		newMentor.setMentorName(mentor.getMentorName());
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("pass", mentor.getMentorPassword());
		RestTemplate restTemplate=new RestTemplate();
		String s=restTemplate.getForObject("http://localhost:4444/jwt/getPass/{pass}", String.class,params);
		//mentor.setMentorPassword(new BCryptPasswordEncoder().encode(mentor.getMentorPassword()));
		newMentor.setMentorPassword(s);
		mentorRepository.save(newMentor);
		return newMentor;
	}

	@Override
	public Mentor deleteMentorByMentorId(int mentorId) {
		mentorRepository.deleteById(mentorId);
		return null;
	}

	@Override
	public Mentor addTechnicalCourseByTechnicalCourseIdAndMentorId(int mentorId, int technicalCourseId) {
		Optional<Mentor> tempMentor=mentorRepository.findById(mentorId);
		Mentor mentor=tempMentor.get();
		
		Optional<TechnicalCourse> tempTechnicalCourse=technicalCourseRepository.findById(technicalCourseId);
		TechnicalCourse technicalCourse=tempTechnicalCourse.get();
		
		List<TechnicalCourse> ltechnicalcourse=mentor.getTechnicalCourses();
		ltechnicalcourse.add(technicalCourse);
		
		
		mentor.setTechnicalCourses(ltechnicalcourse);
		mentorRepository.save(mentor);
		return mentor;
	}

}
