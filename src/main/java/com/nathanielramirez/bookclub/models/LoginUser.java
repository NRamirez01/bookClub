package com.nathanielramirez.bookclub.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
    
public class LoginUser {
    
    @NotEmpty(message="")
    @Email(message="")
    private String email;
    
    @NotEmpty(message="")
    @Size(min=8, max=128, message="")
    private String password;
    
    public LoginUser() {/* Empty Constructor */ }

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