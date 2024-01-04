package org.example;
import java.util.Properties;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootApplication
public class SpringEmailDemoApplication {


    String zawartosc = "Your authentication code: ";
    String wiadomosc = zawartosc+generateAuthenticationCode();

    @Autowired
    private EmailSenderService senderService;
    public static void main(String[] args) {
        SpringApplication.run(SpringEmailDemoApplication.class, args);
    }
    @EventListener(ApplicationReadyEvent.class)
    public void sendMail(String mail, int kod ){
        senderService.sendEmail(mail, "Authentication Code",kod);

    }

    private static int generateAuthenticationCode() {
        // Generate a random 4-digit number between 1000 and 9999
        Random random = new Random();
        int min = 1000;
        int max = 9999;
        int authenticationCodeValue = random.nextInt(max - min + 1) + min;

        // Format the code as a 4-digit string
        return authenticationCodeValue;
    }
}
