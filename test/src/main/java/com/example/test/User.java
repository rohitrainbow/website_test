package com.example.test;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class User {
	@NotEmpty
	private String name;

	@NotEmpty
	private String subject;

	@NotEmpty
	private String message;

	@NotEmpty
	@Email
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}