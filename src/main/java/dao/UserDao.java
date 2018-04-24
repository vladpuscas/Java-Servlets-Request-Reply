package dao;

import entities.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Vlad on 23-Oct-17.
 */
public class UserDao {
    private SessionFactory sessionFactory;

    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }catch (HibernateException ex) {
            if(transaction != null)
                transaction.rollback();
        }finally {
            session.close();
        }
    }

    public User findUser(String username) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<User> users = null;
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM User WHERE username = :username");
            query.setParameter("username",username);
            users = query.list();
            transaction.commit();
        }catch (HibernateException e) {
            if(transaction != null)
                transaction.rollback();
        }finally {
            session.close();
        }
        return (users!=null && !users.isEmpty()) ? users.get(0) : null;
    }

}
