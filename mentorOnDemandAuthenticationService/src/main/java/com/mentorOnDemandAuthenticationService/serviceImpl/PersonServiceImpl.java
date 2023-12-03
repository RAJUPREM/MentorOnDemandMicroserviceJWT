package com.mentorOnDemandAuthenticationService.serviceImpl;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mentorOnDemandAuthenticationService.entity.Person;
import com.mentorOnDemandAuthenticationService.exception.PersonNotFoundException;
import com.mentorOnDemandAuthenticationService.external.Admin;
import com.mentorOnDemandAuthenticationService.external.Mentor;
import com.mentorOnDemandAuthenticationService.external.User;
import com.mentorOnDemandAuthenticationService.repository.PersonRepository;
import com.mentorOnDemandAuthenticationService.service.PersonService;


@Service
public class PersonServiceImpl implements PersonService{

	@Autowired
	PersonRepository personRepository;
	
	
	@Override
	public List<Person> getAllPerson() {
		List<Person> persons = personRepository.findAll();
        if(persons.size() == 0){
            throw new PersonNotFoundException("Person is not found");
        }
		return persons;
	}


	@Override
	public Person getPersonById(int personId) {
		Optional<Person> tempPerson=personRepository.findById(personId);
		Person person=tempPerson.get();
		return person;
	}

	@Override
	public void savePerson() {
		
		RestTemplate restTemplate=new RestTemplate();
		
		ResponseEntity<Admin[]> res=restTemplate.getForEntity("http://localhost:1111/admins/",Admin[].class);
		
		Admin[] ladmin=res.getBody();
		//List<Admin> ladmin=(List<Admin>) RestTemplate.getForObject("http://localhost:1111/admins/", Admin.class);
		
		List<Person> lperson=personRepository.findAll();

		for(Admin admin:ladmin)
		{
			Optional<Person> tempAdmin=personRepository.findByPersonName(admin.getAdminName());
			
			if(tempAdmin.isEmpty())
			{
				Person person=new Person();
				person.setPassword(admin.getAdminPassword());
				person.setPersonName(admin.getAdminName());
				person.setRole("ADMIN");
				personRepository.save(person);
			}
		}
		
		Mentor[] lmentor=restTemplate.getForEntity("http://localhost:2222/mentors/", Mentor[].class).getBody();
		
		for(Mentor mentor:lmentor)
		{
			Optional<Person> tempMentor=personRepository.findByPersonName(mentor.getMentorName());
			if(tempMentor.isEmpty())
			{
				Person person=new Person();
				person.setPassword(mentor.getMentorPassword());
				person.setPersonName(mentor.getMentorName());
				person.setRole("MENTOR");
				personRepository.save(person);
			}
		}
		
		
		User[] luser=restTemplate.getForObject("http://localhost:3333/users/", User[].class);
		
		for(User user:luser)
		{
			Optional<Person> tempUser=personRepository.findByPersonName(user.getUserName());
			if(tempUser.isEmpty())
			{
				Person person=new Person();
				person.setPassword(user.getPassword());
				person.setPersonName(user.getUserName());
				person.setRole("USER");
				personRepository.save(person);
			}
		}
		
	}

	@Override
	public Person updatePersonByPersonId(Person person, int personId) {
		Optional<Person> tempPerson = personRepository.findById(personId);
		Person newPerson=tempPerson.get();
		newPerson.setPersonName(person.getPersonName());
		newPerson.setPassword(new BCryptPasswordEncoder().encode(person.getPassword()));
		newPerson.setRole(person.getRole());
		personRepository.save(newPerson);
		return newPerson;
	}

	@Override
	public Person deletePersonByPersonId(int personId) {
		Optional<Person> tempPerson = personRepository.findById(personId);
		Person person=tempPerson.get();
		personRepository.delete(person);
		return person;
	}
	
	
}
