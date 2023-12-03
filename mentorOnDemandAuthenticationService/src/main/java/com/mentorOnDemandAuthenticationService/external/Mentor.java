package com.mentorOnDemandAuthenticationService.external;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;


public class Mentor {
	
	

	public Mentor(int mentorId, String mentorName, String mentorPassword, int mentorExperience) {
		super();
		this.mentorId = mentorId;
		this.mentorName = mentorName;
		this.mentorPassword = mentorPassword;
		this.mentorExperience = mentorExperience;
	}

	public Mentor() {
		super();
	}

	
	private int mentorId;
	private String mentorName;
	private String mentorPassword;
	private int mentorExperience;
	
	

	public int getMentorId() {
		return mentorId;
	}

	public void setMentorId(int mentorId) {
		this.mentorId = mentorId;
	}

	public String getMentorName() {
		return mentorName;
	}

	public void setMentorName(String mentorName) {
		this.mentorName = mentorName;
	}

	public String getMentorPassword() {
		return mentorPassword;
	}

	public void setMentorPassword(String mentorPassword) {
		this.mentorPassword = mentorPassword;
	}

	public int getMentorExperience() {
		return mentorExperience;
	}

	public void setMentorExperience(int mentorExperience) {
		this.mentorExperience = mentorExperience;
	}

	@Override
	public String toString() {
		return "Mentor [mentorId=" + mentorId + ", mentorName=" + mentorName + ", mentorPassword=" + mentorPassword
				+ ", mentorExperience=" + mentorExperience + "]";
	}

	

	

	

}
