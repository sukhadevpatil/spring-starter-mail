package com.mail.mailsender.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.MessageFormat;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEMail(String to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setFrom(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        javaMailSender.send(simpleMailMessage);

        log.info(MessageFormat.format("Email has been sent ...{0}", to));
    }

    @Override
    public void sendEMail(String[] to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setFrom(to[0]);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        javaMailSender.send(simpleMailMessage);

        log.info(MessageFormat.format("Email has been sent ...{0}", to));
    }

    @Override
    public void sendEMailWithHTML(String[] to, String subject, String message) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setTo(to);
        helper.setFrom(to[0]);
        helper.setSubject(subject);
        helper.setText(message, true);

        javaMailSender.send(mimeMessage);
        log.info(MessageFormat.format("Email has been sent ...{0}", "html content"));
    }

    @Override
    public void sendEMailWithAttachment(String[] to, String subject, String message, File file) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setTo(to);
        helper.setFrom(to[0]);
        helper.setSubject(subject);
        helper.setText(message, true);

        helper.addAttachment(file.getName(), file);

        javaMailSender.send(mimeMessage);
        log.info(MessageFormat.format("Email has been sent ...{0}", "With File Attachment"));
    }
}
