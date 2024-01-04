package org.example;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailSenderService {


    private static JavaMailSender createMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("mikolajwojnowski0@gmail.com");
        mailSender.setPassword("czby dqjk rnbz cqcz");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.starttls.required", true);

        return mailSender;
    }
   // @Autowired
    //private JavaMailSender mailSender;
    public void sendEmail(String toEmail, String subject, int body)
    {
        JavaMailSender mailSender = createMailSender();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("wojnowskimikolaj0@gmail.com");
        message.setTo(toEmail);
        message.setText(String.valueOf(body));
        message.setSubject(subject);
        mailSender.send(message);

        System.out.println("mail send succesfully...");



    }

}