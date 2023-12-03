package com.userService.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.userService.entity.User;
import com.userService.repository.UserRepository;
import com.userService.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<User> getAllUser() {
		List<User> luser=userRepository.findAll();
		return luser;
	}

	@Override
	public User getUserById(int userId) {
		Optional<User> tempUser=userRepository.findById(userId);
		User user=tempUser.get();
		return user;
	}

	@Override
	public User saveUser(User user) {
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("pass", user.getPassword());
		RestTemplate restTemplate=new RestTemplate();
		String s=restTemplate.getForObject("http://localhost:4444/jwt/getPass/{pass}", String.class,params);
		user.setPassword(s);
		User use=userRepository.save(user);
		return use;
	}

	@Override
	public User updateUserByUserId(User user, int userId) {
		Optional<User> tempUser=userRepository.findById(userId);
		User use=tempUser.get();
		use.setUserName(user.getUserName());
		Map<String, String> params = new HashMap<String, String>();
		params.put("pass", user.getPassword());
		RestTemplate restTemplate=new RestTemplate();
		String s=restTemplate.getForObject("http://localhost:4444/jwt/getPass/{pass}", String.class,params);
		use.setPassword(s);
		userRepository.save(use);
		return use;
	}

	@Override
	public User deleteUserByUserId(int userId) {
		userRepository.deleteById(userId);
		return null;
	}

}
