package com.Tomcat;

import com.Tomcat.email.EmailSender;
import com.Tomcat.util.TokenGenerator;

public class test {

    public static void main(String[] args) {
        EmailSender.sendEmail("movsesharutyunyan20@Gmail.com", "Your Verification Code"
                , EmailSender.generateEmailContent(TokenGenerator.generateVerifyToken()));
    }
}
