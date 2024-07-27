package com.mail.mailsender.service;

import jakarta.mail.MessagingException;

import java.io.File;

public interface EmailService {

    void sendEMail(String to, String subject, String message);
    void sendEMail(String []to, String subject, String message);
    void sendEMailWithHTML(String []to, String subject, String message) throws MessagingException;
    void sendEMailWithAttachment(String []to, String subject, String message, File file) throws MessagingException;
}
