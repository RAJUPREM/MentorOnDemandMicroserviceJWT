package com.userService.external;

import java.util.List;




public class TechnicalCourse {
	
	public TechnicalCourse() {
		super();
	}

	public TechnicalCourse(int technicalCourseId, String technicalCourseName, List<Mentor> mentors) {
		super();
		this.technicalCourseId = technicalCourseId;
		this.technicalCourseName = technicalCourseName;
		this.mentors = mentors;
	}


	private int technicalCourseId;
	private String technicalCourseName;
	

	private List<Mentor> mentors;

	public int getTechnicalCourseId() {
		return technicalCourseId;
	}

	public void setTechnicalCourseId(int technicalCourseId) {
		this.technicalCourseId = technicalCourseId;
	}

	public String getTechnicalCourseName() {
		return technicalCourseName;
	}

	public void setTechnicalCourseName(String technicalCourseName) {
		this.technicalCourseName = technicalCourseName;
	}

	public List<Mentor> getMentors() {
		return mentors;
	}

	public void setMentors(List<Mentor> mentors) {
		this.mentors = mentors;
	}

	@Override
	public String toString() {
		return "TechnicalCourse [technicalCourseId=" + technicalCourseId + ", technicalCourseName="
				+ technicalCourseName + ", mentors=" + mentors + "]";
	}
	

}
