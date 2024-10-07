package com.Tomcat.controller;

import com.Tomcat.exception.GeneralException;
import com.Tomcat.exception.UserNotFoundException;
import com.Tomcat.exception.VerifayException;
import com.Tomcat.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VerifyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = (String) req.getSession().getAttribute("email");
        String code = req.getParameter("code");
        System.out.println(email);
        System.out.println(code);
        String errorMess = null;
        try {
            UserService.verifyUser(email, code);
        } catch (Exception e) {
            if (e instanceof VerifayException ||
                    e instanceof UserNotFoundException ||
                    e instanceof GeneralException) {
                errorMess = e.getMessage();
            }
            if (e instanceof UserNotFoundException) {
                errorMess = e.getMessage();
            }
        }
        if (errorMess != null) {
            req.setAttribute("errorMess", errorMess);
            req.getRequestDispatcher("verify_page.jsp").forward(req, resp);
        }
        req.getSession().invalidate();
        resp.sendRedirect("/sign-in.jsp");
    }
}
