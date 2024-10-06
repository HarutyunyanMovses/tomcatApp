package com.Tomcat.controller;

import com.Tomcat.email.EmailSender;
import com.Tomcat.enums.Status;
import com.Tomcat.exception.ResurceAlreadyExistsException;
import com.Tomcat.exception.ValidationException;
import com.Tomcat.model.User;
import com.Tomcat.repository.UserRepository;
import com.Tomcat.service.UserService;
import com.Tomcat.util.TokenGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String year = req.getParameter("year");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            UserService.saveUser(name, surname, year, email, password);
            req.getSession().setAttribute("email", email);
            EmailSender.sendEmail(email, "Your Verification Code"
                    , EmailSender.generateEmailContent(TokenGenerator.generateVerifyToken()));
        } catch (Exception e) {
            if (e instanceof ValidationException || e instanceof ResurceAlreadyExistsException) {
                req.setAttribute("errorMess", e.getMessage());
                req.getRequestDispatcher("/signUp.jsp").forward(req, resp);
            }
        }
        resp.sendRedirect("/verify_page.jsp");
    }
}
