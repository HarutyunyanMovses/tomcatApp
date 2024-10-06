package com.Tomcat.service;

import com.Tomcat.email.EmailSender;
import com.Tomcat.enums.Status;
import com.Tomcat.exception.*;
import com.Tomcat.helpers.UserHelper;
import com.Tomcat.model.User;
import com.Tomcat.repository.UserRepository;
import com.Tomcat.util.TokenGenerator;

import java.sql.SQLException;

public class UserService {
    public static void saveUser(String name, String surname, String year, String email, String password) {
        if (year == null) {
            throw new ValidationException("year", "Year cannot be null");
        } else if (!year.matches("^[0-9]{4}$")) {
            throw new ValidationException("Year", "Year cannot be more than 4 digits and non-digit");
        } else {
            User user = new User(0, name, surname, Integer.parseInt(year), email, password,
                    Status.INACTIVE, TokenGenerator.generateVerifyToken(), null);
            try {
                UserHelper.validateUser(user);
                User userDB = UserRepository.getUserByEmail(user.getEmail());
                if (userDB != null) {
                    throw new ResurceAlreadyExistsException("Exist", "User already exists");
                } else {
                    user.setPassword(TokenGenerator.passwordEncoder(password));
                    UserRepository.createUser(user);
                }
            } catch (Exception e) {
                if (e instanceof ResurceAlreadyExistsException) {
                    throw new ResurceAlreadyExistsException("Exist", "User already exists");
                }
                if (e instanceof ValidationException) {
                    throw (ValidationException) e;
                }
            }
        }
    }

    public static void verifyUser(String email, String code) throws SQLException {
        User user = UserRepository.getUserByEmail(email);

        if (user != null && user.getVerifyCode() != null && user.getVerifyCode().equals(code)) {
            UserRepository.updateUserStatus(email);
        } else if (user.getVerifyCode() == null) {
            throw new VerifayException("You are Verifyaed");
        }

    }

    public static void SendResetToken(String email) throws SQLException {
        String reset_token = TokenGenerator.generateVerifyToken();
        User user = UserRepository.getUserByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        try {
            UserRepository.updateUserResetToken(email, reset_token);
            EmailSender.sendEmail(email, "Your Reset Token", EmailSender.generateEmailContent(reset_token));
        } catch (Exception e) {
            if (e instanceof SQLException) {
                throw new GeneralException("Please try later ");
            }
        }

    }

    public static void confirmForgot(String email, String reset_token, String password, String confirmPassword)
            throws SQLException {
        UserHelper.validatePassword(password);
        if (!password.equals(confirmPassword)) {
            throw new ValidationException("errorMessage", "Passwords do not match");
        }
        User user = UserRepository.getUserByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        if (!user.getResetToken().equals(reset_token)) {
            throw new ValidationException("errorMessage", "Reset token does not match");
        }
        UserRepository.forgotPasswordUpdate(email, password);
    }

}
