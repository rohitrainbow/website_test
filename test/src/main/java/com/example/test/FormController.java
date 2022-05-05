package com.example.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;

@Controller
@CrossOrigin(origins = "http://localhost") 
public class FormController {
	@Autowired
	SendingMailService sendingMailService;

	@PostMapping("/form")
	ResponseEntity<?> addUser(User user) {
		/*
		 * if (bindingResult.hasErrors()) { return "form"; }
		 */
		try {
			// String subject = user.getName() + " " + user.getEmail() + " sent you a
			// message";
			sendingMailService.sendMail(user.getName(), user.getSubject(), user.getMessage(), user.getEmail());
			user = null;
			return ResponseEntity.ok("success");
		} catch (Exception e) {
			return ResponseEntity.ok("failed");
		}
	}
}