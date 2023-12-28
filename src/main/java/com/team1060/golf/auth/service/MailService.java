package com.team1060.golf.auth.service;

import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.ISpringTemplateEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.util.Random;
/**
 * <pre>
 * 메일 인증 
 * </pre>
 * 
 * @author KJY
 * @since 2023.12.28
 */
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender emailSender;
    private final ISpringTemplateEngine templateEngine;
    private String authNum;

    public void createCode() {
        Random random = new Random();
        StringBuilder key = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            int type = random.nextBoolean() ? 'a' : '0';
            key.append((char) ((int) random.nextInt(type == 'a' ? 26 : 10) + type));
        }

        authNum = key.toString();
    }

    public MimeMessage createEmailForm(String email) throws MessagingException, UnsupportedEncodingException {
        createCode();
        String setFrom = "fanalgolfteam@gmail.com";
        String toEmail = email;
        String title = "골프의 민족 인증번호";

        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, toEmail);
        message.setSubject(title);
        message.setFrom(setFrom);
        message.setText(setContext(authNum), "utf-8", "html");

        return message;
    }

    public String sendEmail(String toEmail) throws MessagingException, UnsupportedEncodingException {
        MimeMessage emailForm = createEmailForm(toEmail);
        emailSender.send(emailForm);

        return authNum;
    }

    public String setContext(String code) {
        Context context = new Context();
        context.setVariable("code", code);
        return templateEngine.process("mail", context);
    }
}
