package com.Tomcat.repository.userRepositoryImpl;

import com.Tomcat.enums.Status;
import com.Tomcat.model.enttis.User;
import com.Tomcat.repository.UserRepository;
import com.Tomcat.util.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryJDBC implements UserRepository {
    public void createUser(User user) {
        try {
            Connection conn = ConnectionDB.getConnection();
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
            throw new RuntimeException(ex);
        }
    }

    public User getUserById(int id) {
        User user = null;
        try {
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            pstmt.setInt(1, id);
            List<User> users = parseUser(pstmt.executeQuery());
            if (users.size() > 0) user = users.get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public User getUserByEmail(String email) {
        User user = null;
        try {
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE email = ?");
            pstmt.setString(1, email);
            List<User> users = parseUser(pstmt.executeQuery());
            if (users.size() > 0) user = users.get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(int id) {

    }

    public void updateUserStatus(int id) {
        Connection connection = ConnectionDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("update users set status=?,verify_code=null where id=?");
            preparedStatement.setString(1, Status.ACTIVE.name());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUserResetToken(int id, String reset_token) {
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection
                    .prepareStatement("update users set reset_token=? where id=?");
            preparedStatement.setString(1, reset_token);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void forgotPasswordUpdate(int id, String password) {
        try {
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement preparedStatement = connection.
                    prepareStatement("update users set password=?,reset_token = null where id=?");
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private List<User> parseUser(ResultSet rs) throws SQLException {

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
}
