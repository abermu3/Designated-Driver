package com.dojo.bacproject.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.dojo.bacproject.models.LoginUser;
import com.dojo.bacproject.models.User;
import com.dojo.bacproject.repositories.UserRepo;

@Service
public class UserService {

	@Autowired
	UserRepo userRepo;
	
	public User register(User newUser, BindingResult result) {
		
		Optional<User> incomingUser = userRepo.findByEmail(newUser.getEmail());
    	
    	// Reject if email is taken (present in database)
    	if(incomingUser.isPresent()) {
    		result.rejectValue("email", "Matches", "Email is already in use");
    	}
    	
        // Reject if password doesn't match confirmation
    	if(!newUser.getPassword().equals(newUser.getConfirm())) {
    		result.rejectValue("confirm", "Matches", "Passwords do no match");
    	}
    	
    	// Return null if result has errors
    	if(result.hasErrors()) {
    		return null;
    	}
    
        // Hash and set password, save user to database
    	String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    	newUser.setPassword(hashed);
    	return userRepo.save(newUser);
    	
	}
	
	public User login(LoginUser newLogin, BindingResult result) {

		Optional<User> incomingUser = userRepo.findByEmail(newLogin.getEmail());
        
    	// Find user in the DB by email
        // Reject if NOT present
    	if(!incomingUser.isPresent()) {
    		result.rejectValue("email", "Matches", "User does not exist");
    		return null;
    	}
    	
    	// User exists, retrieve user from DB
    	User userFromDb = incomingUser.get();
        
        // Reject if BCrypt password match fails
    	if(!BCrypt.checkpw(newLogin.getPassword(), userFromDb.getPassword())) {
    	    result.rejectValue("password", "Matches", "Invalid Password");
    	}
    	
    	// Return null if result has errors
    	if(result.hasErrors()) {
    		return null;
    	}
    	
        // Otherwise, return the user object
        return userFromDb;
        
    }
	
	public List<User> allUsers(){
		return userRepo.findAll();
	}
	
	public User updateUser(User user) {
		return userRepo.save(user);
	}
	
	public User findById(Long id) {
		Optional<User> incomingUser = userRepo.findById(id);
		if(incomingUser.isPresent()) {
			return incomingUser.get();
		}else {
			return null;
		}
	}
}