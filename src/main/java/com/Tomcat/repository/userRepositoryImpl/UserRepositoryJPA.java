package com.Tomcat.repository.userRepositoryImpl;

import com.Tomcat.enums.Status;
import com.Tomcat.model.enttis.User;
import com.Tomcat.repository.UserRepository;
import com.Tomcat.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserRepositoryJPA implements UserRepository {

    @Override
    public void createUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user); // Save the user
        transaction.commit();
        session.close(); // Ensure session is closed
    }

    @Override
    public User getUserById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = session.get(User.class, id); // Fetch user by ID
        session.close(); // Ensure session is closed
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = session.createQuery("FROM User WHERE email = :email", User.class)
                .setParameter("email", email)
                .uniqueResult(); // Fetch user by email
        session.close(); // Ensure session is closed
        return user;
    }

    @Override
    public void updateUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user); // Update the user
        transaction.commit();
        session.close(); // Ensure session is closed
    }

    @Override
    public void deleteUser(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id); // Fetch user by ID
        if (user != null) {
            session.delete(user); // Delete the user if exists
        }
        transaction.commit();
        session.close(); // Ensure session is closed
    }

    @Override
    public void updateUserStatus(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id); // Fetch user by ID
        if (user != null) {
            user.setStatus(Status.ACTIVE); // Toggle user status
            session.update(user); // Update the user
        }
        transaction.commit();
        session.close(); // Ensure session is closed
    }

    @Override
    public void updateUserResetToken(int id, String resetToken) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id); // Fetch user by ID
        if (user != null) {
            user.setResetToken(resetToken); // Set the reset token
            session.update(user); // Update the user
        }
        transaction.commit();
        session.close(); // Ensure session is closed
    }

    @Override
    public void forgotPasswordUpdate(int id, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id); // Fetch user by ID
        if (user != null) {
            user.setPassword(password); // Update the password
            session.update(user); // Update the user
        }
        transaction.commit();
        session.close(); // Ensure session is closed
    }
}
