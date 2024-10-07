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
        try {
            login = UserService.login(email, password);
        } catch (Exception e) {
            if (e instanceof ValidationException ||
                    e instanceof GeneralException) {
                errorMessage = e.getMessage();

                if (e instanceof ResurceAlreadyExistsException) {
                    req.getSession().setAttribute("email",email);
                    req.getRequestDispatcher("/verify-page.jsp").forward(req,resp);
                }
            }
        }
        if (errorMessage == null) {
            session.setAttribute("user", login);
            req.getRequestDispatcher("home-page.jsp").forward(req,resp);
        } else {
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("sign-in.jsp").forward(req, resp);
        }
    }
}
