package com.mentorOnDemandAuthenticationService.service;

import java.util.List;

import com.mentorOnDemandAuthenticationService.entity.Person;



public interface PersonService {
	
	public List<Person> getAllPerson();
	
	public Person getPersonById(int personId);
	
	public void savePerson();
	
	public Person updatePersonByPersonId( Person person, int personId);
	
	public Person deletePersonByPersonId(int personId);
	
	public Person saveDefaultAdmin();

}
