package com.Tomcat.email;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailConfig {

    public static void sand(Session session, String from, String to, String subject, String text)
            throws MessagingException {
        Message message = new MimeMessage(session);
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setFrom(new InternetAddress(from));
        message.setSubject(subject);
        message.setContent(text, "text/html");

        Transport.send(message);
    }
}