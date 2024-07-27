package com.mail.mailsender;

import com.mail.mailsender.service.EmailService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
public class EmailSenderTest {

    @Autowired
    EmailService emailService;

    @Test
    void sendMailTest() {
        emailService.sendEMail("dummy@gmail.com", "Test Email Subject", "Email Content");
    }

    @Test
    void sendMailTestWithHtml() throws MessagingException {
        String html = """
                <h1 style='color:red;border:1'>This is Header for Testing</h1>
                """;
        emailService.sendEMailWithHTML(new String[]{"dummy@gmail.com", "dummy2@gmail.com"}, "Test Email Subject", html);
    }

    @Test
    void sendMailTestAttachment() throws MessagingException {
        String html = """
                <h1 style='color:red;border:1'>This is Header for Testing</h1>
                """;
        emailService.sendEMailWithAttachment(new String[]{"dummy@gmail.com", "dummy2@gmail.com"},
                "Test Email Subject",
                html,
                new File("D:\\workspace\\spring-mail\\mailsender\\src\\main\\resources\\alien.draw.jpg"));
    }
}
