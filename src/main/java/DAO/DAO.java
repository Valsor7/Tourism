package DAO;

import DAO.DAO;
import GUI.SearchFrame;
import GUI.ToursFrame;
import models.*;
import org.hibernate.*;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metamodel.domain.Entity;
import org.hibernate.sql.Insert;
import org.hibernate.sql.JoinType;
import org.hibernate.sql.Update;
import util.HibernateUtil;

import javax.swing.*;
import java.util.*;

/**
 * Created by yaroslav on 12.11.2014.
 */
public class DAO {

    public List<Object> getListEntity(String myQuery) {
        Session session = null;
        List<Object> entityList = new ArrayList<>();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query query = session.createQuery(myQuery);

            entityList = query.list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();

        }

        return entityList;
    }

    public void setClient(ClientsEntity client) {
        Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            session.save(client);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Object getEntityById(String myQuery, int id) {
        Session session = null;
        Object entityObj = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query query = session.createQuery(myQuery);
            query.setLong("id", id);
            entityObj = query.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return entityObj;
    }

    public void setOrder(OrdersEntity order) {
        Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(order);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public OrdersEntity getLastOrder() {

        OrdersEntity orderEntity = new OrdersEntity();
        Session session = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query query = session.createQuery("from OrdersEntity order by id DESC");
            query.setMaxResults(1);
            orderEntity = (OrdersEntity) query.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return orderEntity;
    }

    public ClientsEntity getLastClient() {

        ClientsEntity clientsEntity = new ClientsEntity();
        Session session = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query query = session.createQuery("from ClientsEntity order by id DESC");
            query.setMaxResults(1);
            clientsEntity = (ClientsEntity) query.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return clientsEntity;
    }

    public List<Object>  criteria(List<Object> columnParams, Integer[] priceFromTo) {
        Session session = null;
        String[] paramsNames = {"c.country", "city.city", "h.stars", "h.board", "t.price"};
        Map<String, Object> searchParams = new HashMap<>();
        List<Object> list = new ArrayList<>();
        int i = 0;
        for (Object param : columnParams) {
            if (!param.equals(0)) {
                searchParams.put(paramsNames[i], (param));
            }
            i++;
        }


        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria toursCriteria = session.createCriteria(ToursEntity.class, "t");
            toursCriteria.createAlias("t.hotelsByHotelId", "h");
            toursCriteria.createAlias("h.citiesByCityId", "city");
            toursCriteria.createAlias("city.countriesByCountryId", "c");
            toursCriteria.add(Restrictions.allEq(searchParams)).add(Restrictions.between("t.price", priceFromTo[0], priceFromTo[1]));

            list = toursCriteria.list();


        } catch (HibernateException e) {

            e.printStackTrace();
        } finally {
            session.close();
        }

        return list;
    }

    public List<Object> getCity(Object country){
        Session session = null;
        List<Object> citiesList = new ArrayList<>();
        try {

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("select city.city from CitiesEntity city INNER JOIN" +
                    " city.countriesByCountryId c where c.country= :country");
            query.setParameter("country", country);
            citiesList = query.list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return citiesList;
    }
}
