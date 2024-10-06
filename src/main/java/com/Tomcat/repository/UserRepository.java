package com.Tomcat.repository;

import com.Tomcat.enums.Status;
import com.Tomcat.exception.GeneralException;
import com.Tomcat.exception.UserNotFoundException;
import com.Tomcat.exception.VerifayException;
import com.Tomcat.model.User;
import com.Tomcat.util.ConnectionDB;
import com.Tomcat.util.TokenGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public static void createUser(User user) {
        Connection conn = ConnectionDB.getConnection();
        System.out.println("*************************************");
        try {
            PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO users VALUES (0 ,?, ?, ?, ?, ?, ?, ?, ?)"
            );
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getSurname());
            pstmt.setInt(3, user.getYear());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getPassword());
            pstmt.setString(6, user.getStatus().name());
            pstmt.setString(7, user.getVerifyCode());
            pstmt.setString(8, user.getResetToken());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
        System.out.println("userCreated");
    }

    public static User getUserById(int id) {
        User user = null;
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            pstmt.setInt(1, id);
            List<User> users = parseUser(pstmt.executeQuery());
            if (users.size() > 0) {
                user = users.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static User getUserByEmail(String email) throws SQLException {
        User user = null;
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE email = ?")) {
            pstmt.setString(1, email);
            List<User> users = parseUser(pstmt.executeQuery());
            if (users.size() > 0) {
                user = users.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return user;
    }

    public static void updateUserStatus(String email) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("update users set status=?,verify_code=null where email=?");
        preparedStatement.setString(1, Status.ACTIVE.name());
        preparedStatement.setString(2, email);
        try {
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            if (e instanceof VerifayException) {
                throw new VerifayException("Verification filed");
            }
        }
    }

    public static void updateUserResetToken(String email, String reset_token) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = connection
                .prepareStatement("update users set reset_token=? where email=?");
        preparedStatement.setString(1, reset_token);
        preparedStatement.setString(2, email);
        preparedStatement.executeUpdate();

    }

    public static void forgotPasswordUpdate(String email,String password) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = connection.
                prepareStatement("update users set password=?,reset_token = null where email=?");
        preparedStatement.setString(1, TokenGenerator.passwordEncoder(password));
        preparedStatement.setString(2, email);
        preparedStatement.executeUpdate();
    }

    private static List<User> parseUser(ResultSet rs) throws SQLException {

        List<User> users = new ArrayList<>();
        if (rs.next()) {
            users.add(
                    new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getInt("year"),
                            rs.getString("email"),
                            rs.getString("password"),
                            Status.valueOf(rs.getString("status")),
                            rs.getString("verify_code"),
                            rs.getString("reset_token")
                    ));
        }
        return users;
    }

    public static boolean validateUser(String email, String password) throws SQLException {
        Connection conn = ConnectionDB.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("select email,password from users where email = ?");
        pstmt.setString(1, email);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            String email1 = rs.getString("email");
            String password1 = rs.getString("password");
            if (email.equals(email1) && password.equals(password1)) {
                return true;
            }
        }
        throw new GeneralException("invalid fields");
    }
}
