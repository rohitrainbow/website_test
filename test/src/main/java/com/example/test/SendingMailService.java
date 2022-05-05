package com.example.test;

public interface SendingMailService {
	boolean sendMail(String name, String subject, String body, String to);
}