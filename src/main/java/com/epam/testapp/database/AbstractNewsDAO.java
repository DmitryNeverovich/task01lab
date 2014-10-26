/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.testapp.database;

import com.epam.testapp.database.connectionpool.ConnectionPool;
import com.epam.testapp.model.News;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Abstract class defines methods for DAO
 * 
 */

public abstract class AbstractNewsDAO {

    /**
     * Create List of News objects, with information about all find object in DB
     * @return
     * @throws DAOException if a database access error occurs
     */
    public abstract List<News> getList()throws DAOException;

    /**
     * Save information about entity in DB
     * @param news
     * @return
     * @throws DAOException if a database access error occurs
     */
    public abstract News save(News news)throws DAOException;

    /**
     * Delete information about entity in DB
     * @param id
     * @return
     * @throws DAOException if a database access error occurs
     */
    public abstract boolean remove(Integer[] id)throws DAOException;

    /**
     * Create News object, with information about specific object in DataBase
     * @param id
     * @return
     * @throws DAOException if a database access error occurs
     */
    public abstract News findById(int id)throws DAOException;
    
    /**
     * Close statement
     * @param statement
     * @throws DAOException if a database access error occurs
     */
    public void closeSatement(Statement statement) throws DAOException{
         try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                throw new DAOException("Statement can not be closed", ex);
            }
    }
    
    /**
     * Close ResultSet
     * @param resultSet
     * @throws DAOException if a database access error occurs
     */
    public void closeResultSet(ResultSet resultSet) throws DAOException{
         try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException ex) {
                throw new DAOException("Result set an not be closed", ex);
            }
    }

}
