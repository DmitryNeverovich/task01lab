/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.testapp.database.impl;

import com.epam.testapp.database.AbstractNewsDAO;
import com.epam.testapp.database.DAOException;
import com.epam.testapp.model.News;
import com.epam.testapp.util.GeneratorDeleteQuery;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of JPA DAO for database
 * @author Dzmitry_Neviarovich
 */
public class NewsDAOJPA extends AbstractNewsDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public List<News> getList() throws DAOException {
        TypedQuery<News> namedQuery = null;
        try {
            namedQuery = entityManager.createNamedQuery("News.getAll", News.class);
//            namedQuery = entityManager.createQuery("SELECT c from News c", News.class);
        } catch (Exception e) {
            throw new DAOException("Error in getList",e);
        }

        return namedQuery.getResultList();
    }

    @Transactional
    @Override
    public News save(News news) throws DAOException {
        News newsInsert = entityManager.merge(news);
        return newsInsert;
    }

    @Transactional
    @Override
    public boolean remove(Integer[] id) throws DAOException {
        boolean complete = false;
        String queryRemove = GeneratorDeleteQuery.generateDeleteQuery(id);
        Query query = entityManager.createNativeQuery(queryRemove);
        query.executeUpdate();

        return complete;
    }

    @Transactional
    @Override
    public News findById(int id) throws DAOException {
        return entityManager.find(News.class, id);
    }

}
