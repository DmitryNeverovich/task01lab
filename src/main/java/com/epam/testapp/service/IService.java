/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.testapp.service;

import com.epam.testapp.database.DAOException;
import com.epam.testapp.model.News;
import java.util.List;

/**
 *
 * @author Dima
 */
public interface IService {
    
    /**
     * Create List of News objects, with information about all find object in DB
     * @return
     * @throws DAOException if a database access error occurs
     */
    public abstract List<News> getList()throws ServiceException;

    /**
     *
     * @param news
     * @return
     * @throws DAOException if a database access error occurs
     */
    public abstract News save(News news)throws ServiceException;

    /**
     * Delete information about entity in DB
     * @param id
     * @return
     * @throws DAOException if a database access error occurs
     */
    public abstract boolean remove(Integer[] id)throws ServiceException;

    /**
     * Create News object, with information about specific object in DataBase
     * @param id
     * @return
     * @throws DAOException if a database access error occurs
     */
    public abstract News findById(int id)throws ServiceException;
    
}
