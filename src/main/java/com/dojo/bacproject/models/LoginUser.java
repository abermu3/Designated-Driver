package com.dojo.bacproject.models;

import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class LoginUser {
	
	@NotEmpty(message="Email is required!")
    @Email(message="Enter a valid email")
    private String email;
    
    @NotEmpty(message="Password is required!")
    @Size(min=10, max=200, message="Password must be between 10 and 200 characters")
    private String password;
    
    public LoginUser() {}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}

