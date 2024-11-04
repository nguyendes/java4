package org.example.sys.domain.repository.impl;


import org.example.sys.domain.entity.User;
import org.example.config.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.HibernateException;

public class UserRepository {
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
        String hql = "FROM User WHERE username = :username AND password = :password";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            user = query.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return user;
    }
}
