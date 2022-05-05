package com.example.test;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class SESService implements SendingMailService {

	public boolean sendMail(String name, String subject, String body, String recAddress) {
		String to[] = { recAddress };
		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.starttls.enable.required", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", "contactservicenotification@gmail.com");
		props.put("mail.smtp.password", "gaxofajyijfkgrmi");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("contactservicenotification@gmail.com", "gaxofajyijfkgrmi");
			}
		});
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress("contactservicenotification@gmail.com"));
			InternetAddress[] toAddress = new InternetAddress[to.length];

			// To get the array of addresses
			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}

			for (int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}

			message.setSubject("Message Sent Successfully to Rohit Lalwani");
			message.setText("Hi " + name + ",\n\n"
					+ "Thank you for contacting Rohit Lalwani. I will reach out to you as soon as possible"
					+ "\n\nRegards,\nRohit LaLwani,\nconnect@rohitlalwani.com,\n+91-8197790123");
			Transport transport = session.getTransport("smtp");
			transport.connect(host, "contactservicenotification@gmail.com", "gaxofajyijfkgrmi");
			transport.sendMessage(message, message.getAllRecipients());
			toAddress[0] = new InternetAddress("connect@rohitlalwani.com");
			// message.addRecipient(Message.RecipientType.TO, null);
			message.setSubject("Someone Contacted on Website:" + recAddress + ":" + subject);
			message.setText(body);
			for (int i = 0; i < toAddress.length; i++) {
				message.setRecipient(Message.RecipientType.TO, toAddress[i]);
			}
			transport.sendMessage(message, message.getAllRecipients());
			message = null;
			transport.close();
			transport=null;
			return true;
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
		return false;
	}
}
