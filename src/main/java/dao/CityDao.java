package dao;

import entities.City;
import entities.Flight;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Vlad on 29-Oct-17.
 */
public class CityDao {
    private SessionFactory sessionFactory;

    public CityDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addCity(City city) {
        int cityId;
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            cityId = (int)session.save(city);
            city.setId(cityId);
            transaction.commit();
        }catch (HibernateException e) {
            if(transaction != null)
                transaction.rollback();
        }finally {
            session.close();
        }
    }

    public City getCity(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<City> cities = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM City where id=:id");
            query.setParameter("id",id);
            cities = query.list();
            transaction.commit();
        }catch (HibernateException e) {
            if(transaction != null)
                transaction.rollback();
        }finally {
            session.close();
        }
        return (cities != null && !cities.isEmpty()) ? cities.get(0) : null;
    }
}
