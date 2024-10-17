package com.Tomcat.controller.addressController;

import com.Tomcat.model.enttis.Address;
import com.Tomcat.model.enttis.User;
import com.Tomcat.service.AddressService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddAddressServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        String street = req.getParameter("street");
        Address address = new Address(0, country, city, street, user.getId());
        String errorMessage = null;
        try {
            AddressService.addAddress(address);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }

        if (errorMessage != null){
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("sign-in.jsp").forward(req, resp);
        }
        req.getSession().setAttribute("address", address);
        req.getRequestDispatcher("home-page.jsp").forward(req, resp);

    }
}
