package com.Tomcat.email;
import jakarta.mail.*;

import java.util.Properties;

public class EmailSender {

    private static final String PASSWORD = "lkwkufrhccmiebuq";
    private static final String USERNAME = "bookingsystembook@yandex.ru";

    public static void sendEmail(String to, String subject, String text) {
        String host = "smtp.yandex.ru";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.port", "465");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            EmailConfig.sand(session, USERNAME, to, subject, text);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateEmailContent(String code) {
        return "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "<title>Your Verification Code</title>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; margin: 0; }" +
                ".container { background-color: #ffffff; padding: 20px; border-radius: 5px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); }" +
                "h1 { font-size: 24px; color: #333; }" +
                "p { font-size: 16px; line-height: 1.5; color: #555; }" +
                ".code { font-weight: bold; font-size: 18px; color: #007BFF; }" +
                ".footer { margin-top: 20px; font-size: 14px; color: #777; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class=\"container\">" +
                "<h1>Your Verification Code</h1>" +
                "<p>Dear User,</p>" +
                "<p>Thank you for registering with us! Please find your verification code below:</p>" +
                "<p class=\"code\">Verification Code: " + code + "</p>" +
                "<p>To complete your registration, enter this code on our website.</p>" +
                "<p>If you did not initiate this request, please ignore this email.</p>" +
                "<div class=\"footer\">" +
                "<p>Best regards,<br>Movses LLC</p>" +
                "</div></div></body></html>";
    }
}
