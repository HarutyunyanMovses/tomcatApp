package com.Tomcat.controller;

import com.Tomcat.exception.GeneralException;
import com.Tomcat.exception.ResurceAlreadyExistsException;
import com.Tomcat.exception.ValidationException;
import com.Tomcat.model.User;
import com.Tomcat.repository.UserRepository;
import com.Tomcat.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        User login = null;
        String errorMessage = null;
        boolean inactive = false;
        try {
            login = UserService.login(email, password);
        } catch (Exception e) {
            if (e instanceof ValidationException) errorMessage = e.getMessage().split(":")[1];
            if (e instanceof ResurceAlreadyExistsException) {
                errorMessage = e.getMessage();
                inactive = true;
            }
            if (e instanceof GeneralException) errorMessage = e.getMessage();
        }
        if (errorMessage == null) {
            session.setAttribute("user", login);
            req.getRequestDispatcher("home-page.jsp").forward(req, resp);
        } else if (inactive) {
            req.setAttribute("errorMessage", errorMessage.split(":")[1]);
            session.setAttribute("email", email);
            req.getRequestDispatcher("verify_page.jsp").forward(req, resp);
        }
        session.setAttribute("user", login);
        req.getRequestDispatcher("home-page.jsp").forward(req, resp);
    }
}

