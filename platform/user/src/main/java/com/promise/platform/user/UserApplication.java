package com.promise.platform.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * The user application in platform. The user application is designed for
 * Promise platform administrator to manage the registered users.
 *
 */
@SpringBootApplication
@Configuration
public class UserApplication implements CommandLineRunner {
	
    
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		var host = "xxxxx";
//	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//	    mailSender.setHost(host);
//	    mailSender.setPort(587);
//	     
//	    mailSender.setUsername("xxxxx");
//	    mailSender.setPassword("xxxxx");
//	     
//	    Properties props = mailSender.getJavaMailProperties();
//	    props.put("mail.transport.protocol", "smtp");
//	    props.put("mail.smtp.ssl.trust", host);
//	    props.put("mail.smtp.auth", "true");
//	    props.put("mail.smtp.starttls.enable", "true");
//	    props.put("mail.smtp.quitwait", "false");
//	    props.put("mail.debug", "true");
//        Session mailSession = Session.getInstance(props, new Authenticator()
//        {
//
//           protected PasswordAuthentication getPasswordAuthentication()
//             {
//                  return new PasswordAuthentication("xxxxx", "xxxxx");
//             }
//        });	     
//        
//        MimeMessage msg = new MimeMessage(mailSession);
//	    msg.setFrom(new InternetAddress("xxxxxx"));
//	    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("xxxxxxxm", false));
//	    msg.setSubject("A Test email.");
//	    msg.setText("Hello World!");
//	    mailSender.send(msg);		
	}
}
