package com.emailapp.emailservice;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqListener {
    private final JavaMailSender mailSender;

    @Autowired
    // Spring will automatically inject the ObjectMapper used for JSON
    public RabbitMqListener(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    // Spring handles the listening logic for you
    @RabbitListener(queues = "emailQueue")
    public void handleEmailRequest(EmailRequest request) { // Spring deserializes automatically!

        System.out.println("--- Message Consumed ---");
        System.out.println("Attempting to send email to: " + request.getTo());

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("lys41883616@126.com");
        email.setTo(request.getTo());
        email.setSubject(request.getSubject());
        email.setText(request.getBody());

        mailSender.send(email);
        System.out.println("Email sent successfully!");
    }
}
