package com.crawler.com;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailingInformation {
	static final String username = "sender@gmail.com";
	static final String password = "sender_password";
	
	public static void sendMail(String jobDetails) {
	Properties pp = new Properties();
	pp.setProperty("mail.smtp.auth", "true");
	pp.put("mail.smtp.auth", "true");
	pp.put("mail.smtp.starttls.enable", "true");
	pp.put("mail.smtp.host", "smtp.gmail.com");
	pp.put("mail.smtp.port", "587");
	
	// check the authentication
	Session session = Session.getInstance(pp, new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}
	});

	try {

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("sender@gmail.com"));

		// recipients email address
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("receiver@gmail.com"));

		// add the Subject of email
		message.setSubject("Todays Job details");

		// message body
		message.setText(jobDetails);// message

		Transport.send(message);

		System.out.println("Email Sent Successfully");

	} catch (MessagingException e) {
		throw new RuntimeException(e);

	}
	}
}
