package com.sbs.model.otp;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class OTPMailAPI {


public static void sendMail(String toEmail,String subject,String msg) throws Exception
{
	final String email="happyttbank@gmail.com";
	final String password="ssproject";
	Properties props = new Properties();
	
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.socketFactory.port", "465");
	props.put("mail.smtp.socketFactory.class",
			"javax.net.ssl.SSLSocketFactory");
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.port", "465");

	Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(email,password);
				}
			});
	try {
		 
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("HappyTTBanking"));
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(toEmail));
		message.setSubject(subject);
		message.setText(msg);

		Transport.send(message);

		System.out.println("Done");

	} catch (Exception e) {
		throw e;
	}
}
}


