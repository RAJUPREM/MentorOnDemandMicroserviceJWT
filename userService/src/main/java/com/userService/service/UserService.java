package com.userService.service;

import java.util.List;

import com.userService.entity.User;

public interface UserService {
	
	public List<User> getAllUser();
	
	public User getUserById(int userId);
	
	public User saveUser(User user);
	
	public User updateUserByUserId( User user, int userId);
	
	public User deleteUserByUserId(int userId);

}
