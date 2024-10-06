package com.Tomcat.controller;

import com.Tomcat.exception.GeneralException;
import com.Tomcat.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String errorMessage = null;
        // Check credentials
        try {
            UserRepository.validateUser(email, password);
        } catch (Exception e) {
            if (e instanceof GeneralException) {
                errorMessage = e.getMessage();
            }
        }
        if (errorMessage == null) {
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
        resp.sendRedirect("welcom.jsp"); // Redirect to welcome page
    }
}
