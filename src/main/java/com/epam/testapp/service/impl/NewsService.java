package com.epam.testapp.service.impl;

import com.epam.testapp.database.AbstractNewsDAO;
import com.epam.testapp.database.DAOException;
import com.epam.testapp.model.News;
import com.epam.testapp.service.IService;
import com.epam.testapp.service.ServiceException;
import java.util.List;

/**
 * Implementation of the Service layer
 *
 * @author Dima
 */
public class NewsService implements IService {

    private AbstractNewsDAO newsDAO;

    public void setNewsDAO(AbstractNewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }

    @Override
    public List<News> getList() throws ServiceException {

        List<News> newsList = null;
        try {
            newsList = newsDAO.getList();
        } catch (DAOException e) {
            throw new ServiceException("", e);
        }
        return newsList;
    }

    @Override
    public News save(News news) throws ServiceException {

        try {
            news = newsDAO.save(news);
        } catch (DAOException e) {
            throw new ServiceException("", e);
        }
        return news;
    }

    @Override
    public boolean remove(Integer[] id) throws ServiceException {

        boolean complete = false;
        try {
            complete = newsDAO.remove(id);
        } catch (DAOException e) {
            throw new ServiceException("", e);
        }
        return complete;
    }

    @Override
    public News findById(int id) throws ServiceException {
        News news = null;
        try {
            news = newsDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException("", e);
        }
        return news;
    }

}
