package com.Tomcat.controller;

import com.Tomcat.exception.GeneralException;
import com.Tomcat.exception.UserNotFoundException;
import com.Tomcat.exception.ValidationException;
import com.Tomcat.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ForgotPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String errorMessage = null;
        try {
            UserService.SendResetToken(email);
            req.getSession().setAttribute("email", email);
        }
        catch (Exception e) {
            if (e instanceof UserNotFoundException){
                errorMessage = e.getMessage();
            }
            if (e instanceof GeneralException){
                errorMessage = e.getMessage();
            }
        }
        if (errorMessage != null){
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("forgotPassword.jsp").forward(req, resp);
        }
         resp.sendRedirect("confirmForgot.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = (String) req.getSession().getAttribute("email");
        String reset_token = req.getParameter("token");
        String password = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");
        String errorMessage = null;

        try {
            UserService.confirmForgot(email,reset_token,password,confirmPassword);
        }catch (Exception e ){
            if (e instanceof ValidationException){
                errorMessage = e.getMessage();
            }
            if (e instanceof UserNotFoundException){
                errorMessage = e.getMessage();
            }
            if (e instanceof GeneralException){
                errorMessage = e.getMessage();
            }
        }
        if (errorMessage != null){
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("confirmForgot.jsp").forward(req, resp);
        }
        req.getSession().invalidate();
        resp.sendRedirect("index.jsp");
    }
}
