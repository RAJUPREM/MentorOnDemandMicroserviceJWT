package com.mentorOnDemandAuthenticationService.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mentorOnDemandAuthenticationService.entity.JWTRequest;
import com.mentorOnDemandAuthenticationService.entity.JWTResponse;
import com.mentorOnDemandAuthenticationService.entity.Person;
import com.mentorOnDemandAuthenticationService.exception.PersonNotFoundException;
import com.mentorOnDemandAuthenticationService.helper.JWTService;
import com.mentorOnDemandAuthenticationService.repository.PersonRepository;
import com.mentorOnDemandAuthenticationService.serviceImpl.CustomPersonDetailsService;
import com.mentorOnDemandAuthenticationService.serviceImpl.PersonServiceImpl;



@RestController
@RequestMapping("/jwt")
public class JWTController {

    @Autowired
    JWTService jWTService;
    
    @Autowired
    PersonServiceImpl personServiceImpl;
    
    @Autowired
    PersonRepository personRepository;
    
//    @Autowired
//    AdminRepository adminRepository;

    @Autowired
    CustomPersonDetailsService customPersonDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;
    
    @GetMapping("/welcome")	
    public String welcome()
	{
		return "welcome to jwt cotroller";
	}
    
    @GetMapping("/defaultADMIN")	
    public ResponseEntity<Person> defautlADMIN()
	{
    	Person defaultADMIN=personServiceImpl.saveDefaultAdmin();
    	return new ResponseEntity<Person>(defaultADMIN,HttpStatus.OK);
	}

    @PostMapping("/token")
    public ResponseEntity<JWTResponse> getToken(@RequestBody JWTRequest jwtRequest){
        System.out.println(jwtRequest);
        
//        this.personRepository.save(new Person(1, "raju",
// 			   new BCryptPasswordEncoder().encode("Raju@123"), "USER"));
// 		this.personRepository.save(new Person(3, "prem",
// 				new BCryptPasswordEncoder().encode("Prem@123"), "ADMIN"));
// 		this.personRepository.save(new Person(2, "ravi",
//			new BCryptPasswordEncoder().encode("Ravi@123"), "MENTOR"));
   //     adminRepository.save(new Admin(1,"sourabh",new BCryptPasswordEncoder().encode("Sourabh@123")));   
        
        
        personServiceImpl.savePerson();
 		
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getPersonName()
                    , jwtRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                return ResponseEntity.ok(new JWTResponse(jWTService.generateToken(jwtRequest.getPersonName())));
            } else {
                throw new PersonNotFoundException("Person is not found sorry !!");
            }
        }
        catch (Exception ex){
            throw new PersonNotFoundException("user name not found");
        }
    }
    
    @GetMapping("/getPass/{pass}")
    public String getEncPass(@PathVariable(value="pass") String pass)
    {
    	System.out.println(pass);
    	return new BCryptPasswordEncoder().encode(pass);
    }
}
