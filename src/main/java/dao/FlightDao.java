package dao;

import entities.Flight;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

/**
 * Created by Vlad on 25-Oct-17.
 */
public class FlightDao {
    private SessionFactory sessionFactory;

    public FlightDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addFlight(Flight flight) {
        int flightId;
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            flightId = (int)session.save(flight);
            flight.setId(flightId);
            transaction.commit();
        }catch (HibernateException e) {
            if(transaction != null)
                transaction.rollback();
        }finally {
            session.close();
        }
    }

    public List<Flight> getAllFlights() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Flight> flights = null;
        try{
            transaction = session.beginTransaction();
            flights = session.createQuery("FROM Flight").list();
            transaction.commit();
        }catch (HibernateException e) {
            if(transaction != null)
                transaction.rollback();
        }finally {
            session.close();
        }
        return flights;
    }

    public Flight getFlight(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List <Flight> flights = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Flight where id=:id");
            query.setParameter("id",id);
            flights = query.list();
            transaction.commit();
        }catch (HibernateException e) {
            if(transaction != null)
                transaction.rollback();
        }finally {
            session.close();
        }
        return (flights != null && !flights.isEmpty()) ? flights.get(0) : null;
    }

    public void updateFlight(Flight flight, int id) {
        Flight oldFlight = getFlight(id);
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            flight.setId(oldFlight.getId());
            session.update(flight);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
        }finally {
            session.close();
        }
    }

    public void deleteFlight(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Flight flight = null;
        try{
            transaction = session.beginTransaction();
            flight = getFlight(id);
            session.delete(flight);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
        }finally {
            session.close();
        }
    }
}
