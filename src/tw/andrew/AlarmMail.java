package tw.andrew;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AlarmMail {
	
	public AlarmMail() {
		final String username = "datadataplant@gmail.com";
		final String password = "********";
	
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
	
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
	
		try {
	
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("datadataplant@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("datadataplant@gmail.com"));
			message.setSubject("Machine Alarm");
			message.setText("Dear Manager,"
				+ "\n\n Machine 1 may have some problem!");
	
			Transport.send(message);
	
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}

}
