package com.Tomcat.helpers;

import com.Tomcat.exception.ValidationException;
import com.Tomcat.model.User;

public class UserHelper {

    static String namePattern = "^[A-Za-z]+$";
    static String surnamePattern = "^[A-Za-z]+$";
    static String yearPattern = "^(195[0-9]|196[0-9]|197[0-9]|198[0-9]|199[0-9]|200[0-8])$";
    static String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    static String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$\n";

    public static void validateUser(User user) {

        // Validate name
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new ValidationException("Name", "Name cannot be null or empty.");
        } else if (!user.getName().matches(namePattern)) {
            throw new ValidationException("Name", "Name must contain only letters.");
        }

        // Validate surname
        if (user.getSurname() == null || user.getSurname().isEmpty()) {
            throw new ValidationException("Surname", "Surname cannot be null or empty.");
        } else if (!user.getSurname().matches(surnamePattern)) {
            throw new ValidationException("Surname", "Surname must contain only letters.");
        }

        // Validate year
        if (!String.valueOf(user.getYear()).matches(yearPattern)) {
            throw new ValidationException("year", "Year must be between 1950 and 2008.");
        }

        // Validate email
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new ValidationException("Email", "Email cannot be null or empty.");
        } else if (!user.getEmail().matches(emailPattern)) {
            throw new ValidationException("Email", "Email is not valid.");
        }

        // Validate password
        validatePassword(user.getPassword());

    }

    public static void validatePassword(String password) {
        // Validate password
        if (password == null || password.isEmpty()) {
            throw new ValidationException("Password", "Password cannot be null or empty.");
        } else if (password.matches(passwordPattern)) {
            throw new ValidationException("Password", "Password must be at least 8 characters long and contain at least one letter and one number.");
        }
    }

}
