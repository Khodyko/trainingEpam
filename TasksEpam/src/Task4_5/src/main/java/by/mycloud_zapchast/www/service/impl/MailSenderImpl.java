package by.mycloud_zapchast.www.service.impl;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
public class MailSenderImpl implements MailSender {
	public  void send() {   //Exception
		 final String username = "zapchastmycloud@gmail.com";
	        final String password = "huraljmmymxiyhpg";

	        Properties prop = new Properties();
	        prop.put("mail.smtp.host", "smtp.gmail.com");
	        prop.put("mail.smtp.port", "587");
	        prop.put("mail.smtp.auth", "true");
	        prop.put("mail.smtp.starttls.enable", "true"); //TLS
	        
	        Session session = Session.getInstance(prop,
	                new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(username, password);
	                    }
	                });

	        try {

	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("zapchastmycloud@gmail.com"
	            		+ ""));
	            message.setRecipients(
	                    Message.RecipientType.TO,
	                    InternetAddress.parse("gaes1k@yandex.ru")
	            );
	            message.setSubject("Testing Gmail SSL");
	            message.setText("Dear Mail Crawler,"
	                    + "\n\n Please do not spam my email!");

	            Transport.send(message);

	            System.out.println("Done");

	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }
	}

