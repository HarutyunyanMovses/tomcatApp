package com.Tomcat.repository;

import com.Tomcat.model.enttis.User;

public interface UserRepository {
    public void createUser(User user);

    public User getUserById(int id);

    public User getUserByEmail(String email);

    public void updateUser(User user);

    public void deleteUser(int id);

    public void updateUserStatus(int id);

    public void updateUserResetToken(int id, String reset_token);

    public void forgotPasswordUpdate(int id, String password);
}
