/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.testapp.database.impl;

import com.epam.testapp.database.connectionpool.ConnectionPool;
import com.epam.testapp.database.connectionpool.PoolException;
import com.epam.testapp.database.DAOAttributeName;
import com.epam.testapp.database.DAOException;
import com.epam.testapp.database.AbstractNewsDAO;
import com.epam.testapp.model.News;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *  Implementation of DAO for database
 * @author Dzmitry_Neviarovich
 */
public class NewsDAO extends AbstractNewsDAO {

    private ConnectionPool connectionPool;

    private static final String SQL_SELECT_FIND_ALL_QUERY = "SELECT * FROM NEWS_DATA ORDER BY NEWS_DATE DESC";
    private static final String SQL_SELECT_FIND_BY_ID_QUERY = "SELECT * FROM NEWS_DATA WHERE NEWS_ID = ?";
    private static final String SQL_INSERT_NEWS_QUERY = "INSERT INTO NEWS_DATA (TITLE,NEWS_DATE,BRIEF,CONTENT) VALUES (?,?,?,?)";
    private static final String SQL_UPDATE_NEWS_QUERY = "UPDATE NEWS_DATA SET NEWS_DATE=?,BRIEF=?,CONTENT=?,TITLE=? WHERE NEWS_ID=?";
    private static final String SQL_DELETE_NEWS_QUERY = "DELETE FROM NEWS_DATA WHERE NEWS_ID IN (";

    private StringBuilder generateDeleteQuery(Integer[] ids) {
        
        StringBuilder sqlRequestDelete = new StringBuilder(SQL_DELETE_NEWS_QUERY);
        sqlRequestDelete.append(ids[0]);
        
        for (int j = 1; j < ids.length; j++) {
            sqlRequestDelete.append(",");
            sqlRequestDelete.append(ids[j]);
        }
        sqlRequestDelete.append(")");
        
        return sqlRequestDelete;
    }

    /**
     * Create List of News objects, with information about all find object in DB
     * @return
     * @throws DAOException if a database access error occurs
     */        
    @Override
    public List<News> getList() throws DAOException {

        List<News> newsList = null;
        Statement statement = null;
        News news = null;
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            newsList = new ArrayList<>();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_FIND_ALL_QUERY);

            while (resultSet.next()) {
                news = new News();
                news.setId(resultSet.getInt(DAOAttributeName.NEWS_ID));
                news.setTitle(resultSet.getString(DAOAttributeName.TITLE));
                news.setDate(resultSet.getDate(DAOAttributeName.NEWS_DATE));
                news.setBrief(resultSet.getString(DAOAttributeName.BRIEF));
                news.setContent(resultSet.getString(DAOAttributeName.CONTENT));
                newsList.add(news);
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw new DAOException("Error occurred while executing query", ex);
        } catch (PoolException ex) {
            throw new DAOException("Problem with ConnectionPool", ex);
        } finally {
            closeSatement(statement);
            connectionPool.freeConnection(connection);
        }

        return newsList;
    }

    /**
     * Save information about entity in DB
     * @param news
     * @return
     * @throws DAOException if a database access error occurs
     */
    @Override
    public News save(News news) throws DAOException {

        News returnNews = null;
        if (news.getId() == 0) {
            returnNews = create(news);
        } else {
            returnNews = update(news);
        }
        return returnNews;
    }

    private News update(News news) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_NEWS_QUERY);
            preparedStatement.setDate(1, new java.sql.Date(news.getDate().getTime()));
            preparedStatement.setString(2, news.getBrief());
            preparedStatement.setString(3, news.getContent());
            preparedStatement.setString(4, news.getTitle());
            preparedStatement.setInt(5, news.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException("Error occurred while executing query", ex);
        } catch (PoolException ex) {
            throw new DAOException("Problem with ConnectionPool", ex);
        } finally {
            closeSatement(preparedStatement);
            connectionPool.freeConnection(connection);
        }
        return news;
    }

    private News create(News news) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            int result = 0;
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_NEWS_QUERY, new String[]{DAOAttributeName.NEWS_ID});
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setDate(2, new java.sql.Date(news.getDate().getTime()));
            preparedStatement.setString(3, news.getBrief());
            preparedStatement.setString(4, news.getContent());
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
            news.setId(result);
        } catch (SQLException ex) {
            throw new DAOException("Error occurred while executing query", ex);
        } catch (PoolException ex) {
            throw new DAOException("Problem with ConnectionPool", ex);
        } finally {
            closeResultSet(resultSet);
            closeSatement(preparedStatement);
            connectionPool.freeConnection(connection);
        }
        return news;
    }

    /**
     * Delete information about entity in DB
     * @param id
     * @return
     * @throws DAOException if a database access error occurs
     */
    @Override
    public boolean remove(Integer[] ids) throws DAOException {
        boolean complete = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.getConnection();
            StringBuilder sqlRequestDelete = generateDeleteQuery(ids);

            preparedStatement = connection.prepareStatement(sqlRequestDelete.toString());
            preparedStatement.executeUpdate();
            complete = true;
        } catch (SQLException ex) {
            throw new DAOException("Error occurred while executing query", ex);
        } catch (PoolException ex) {
            throw new DAOException("Problem with ConnectionPool", ex);
        } finally {
            closeSatement(preparedStatement);
            connectionPool.freeConnection(connection);
        }
        return complete;
    }

    /**
     * Create News object, with information about specific object in DataBase
     * @param id
     * @return
     * @throws DAOException if a database access error occurs
     */
    @Override
    public News findById(int id) throws DAOException {

        PreparedStatement preparedStatement = null;
        News news = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();

            preparedStatement = connection.prepareStatement(SQL_SELECT_FIND_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                news = new News();
                news.setId(resultSet.getInt(DAOAttributeName.NEWS_ID));
                news.setTitle(resultSet.getString(DAOAttributeName.TITLE));
                news.setDate(resultSet.getDate(DAOAttributeName.NEWS_DATE));
                news.setBrief(resultSet.getString(DAOAttributeName.BRIEF));
                news.setContent(resultSet.getString(DAOAttributeName.CONTENT));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error occurred while executing query", ex);
        } catch (PoolException ex) {
            throw new DAOException("Problem with ConnectionPool", ex);
        } finally {
            closeResultSet(resultSet);
            closeSatement(preparedStatement);
            connectionPool.freeConnection(connection);
        }
        return news;
    }

    public void setConnectionPool(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

}
