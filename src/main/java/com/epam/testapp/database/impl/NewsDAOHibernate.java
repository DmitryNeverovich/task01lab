/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.testapp.database.impl;

import com.epam.testapp.database.AbstractNewsDAO;
import com.epam.testapp.database.DAOException;
import com.epam.testapp.model.News;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 * Implementation of Hibernate DAO for database 
 * @author Dzmitry_Neviarovich
 */
public class NewsDAOHibernate extends AbstractNewsDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<News> getList() throws DAOException {
        Session session = null;
        List<News> newsList = null;

        try {
            session = sessionFactory.openSession();
            newsList = (List<News>) session.createCriteria(News.class).addOrder(Order.desc("date")).list();
        } catch (Exception ex) {
            throw new DAOException("Error getList", ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return newsList;
    }

    @Override
    public News save(News news) throws DAOException {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            if (news.getId() == 0) {
                session.beginTransaction();
                session.save(news);
            } else {
                session.beginTransaction();
                session.update(news);
            }
            session.getTransaction().commit();
        } catch (Exception ex) {
            throw new DAOException("Error save", ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return news;
    }

    @Override
    public boolean remove(Integer[] id) throws DAOException {
        boolean complete = false;
        Session session = null;
        List<Integer> idList = new ArrayList<>();
        
        for (int index = 0; index < id.length; index++) {
            idList.add(id[index]);
        }
        
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.getNamedQuery("deleteAllNews").setParameterList("ids", idList);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            throw new DAOException("Error remove", ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        complete = true;

        return complete;
    }

    @Override
    public News findById(int id) throws DAOException {
        Session session = null;
        News news = null;

        try {
            session = sessionFactory.openSession();
            news = (News) session.get(News.class, id);
        } catch (Exception ex) {
            throw new DAOException("Error findById", ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return news;
    }

}
