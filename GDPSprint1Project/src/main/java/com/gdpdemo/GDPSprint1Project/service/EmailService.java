package com.gdpdemo.GDPSprint1Project.service;

import java.io.UnsupportedEncodingException;
import java.sql.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdpdemo.GDPSprint1Project.Home;
import com.gdpdemo.GDPSprint1Project.Repository.HomeRepository;

import net.bytebuddy.utility.RandomString;

@Service
@Transactional
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    // Sends an OTP message to the provided email
    public void sendOtpMessage(String to, int OTP) throws MessagingException {
        // Create and configure an email message
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(to);
        String subject = "Here's your One Time Password (OTP) - Expire in 5 minutes!";
        String content = "<p>Hello " + to + "</p>" +
            "<p>For security reasons, you're required to use the following " +
            "One Time Password to login:</p>" +
            "<p><b>" + OTP + "</b></p>" +
            "<br>" +
            "<p>Note: this OTP is set to expire in 5 minutes.</p>";

        helper.setSubject(subject);
        helper.setText(content, true);

        // Send the email with the OTP message
        javaMailSender.send(msg);
    }
}
