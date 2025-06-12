package edu.ifmg.produto.services;

import edu.ifmg.produto.dtos.EmailDTO;
import edu.ifmg.produto.services.exceptions.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Autowired
    private JavaMailSender mailSender;

//ESTOU COM ERRO - Username and password not accepted


    public void sendMail(EmailDTO emailDto) {

        //JavaMailSender mailSender = new JavaMailSenderImpl();

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailFrom);
            message.setTo(emailDto.getTo());
            message.setSubject(emailDto.getSubject());
            message.setText(emailDto.getBody());
            mailSender.send(message);
        } catch (MailSendException e) {
            throw new EmailException(e.getMessage());
        }


    }
}
