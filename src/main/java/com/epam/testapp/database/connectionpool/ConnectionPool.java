
package com.epam.testapp.database.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;

/**
 * Implementation of the pool connection to DB
 * 
 */
public final class ConnectionPool {

    private final static Logger logger = Logger.getLogger(ConnectionPool.class);
    
    private final static int SIZE = 10;
    private ArrayBlockingQueue<Connection> connectionsBusy;
    private ArrayBlockingQueue<Connection> connectionsFree;
    
    /**
     * Constructs a connectionpool containing the connections of the specified  database.
     * 
     * @param databaseClassName JDBC driver class name 
     * @param url url database
     * @param user database username
     * @param password database password
     */
    public ConnectionPool(String databaseClassName, String url, String user, String password) throws PoolException {

        connectionsBusy = new ArrayBlockingQueue<Connection>(SIZE);
        connectionsFree = new ArrayBlockingQueue<Connection>(SIZE);
        init(databaseClassName, url, user, password);
    }

     private void init(String databaseClassName, String url, String user, String password) throws PoolException {

        Connection connection;        
        try {
            Class.forName (databaseClassName);
            Locale.setDefault(Locale.ENGLISH);
            for (int i = 0; i < SIZE; i++) {
                connection = DriverManager.getConnection(url,user,password);
                connectionsFree.add(connection);
            }
        } catch (SQLException ex) {
            throw new PoolException("Problem with DriverManager", ex);
        } catch (ClassNotFoundException ex) {
            throw new PoolException("Problem with DriverManager", ex);
        }
    }
    /**
     * Borrows connection from the pool
     * @return Connection
     * @throws PoolException if a database access error occurs
     */
    public Connection getConnection() throws PoolException {
        Connection connectionBusy = null;

        if (!connectionsFree.isEmpty()) {
            try {
                connectionBusy = connectionsFree.poll(5, TimeUnit.MILLISECONDS);
                connectionsBusy.add(connectionBusy);
            } catch (InterruptedException ex) {
                throw new PoolException("Can not be obtained connection", ex);
            }
        }
        return connectionBusy;
    }

    /**
     * Returns connection in the pool
     * @param connection
     * @return true if processing complete, false if processing fail
     */
    public boolean freeConnection(Connection connection){

        boolean complete = false;
        if (connection != null) {
            connectionsBusy.remove(connection);
            connectionsFree.add(connection);
            complete = true;
        }
        return complete;
    }

    /**
     * Close all connections in the pool
     * @return true if processing complete, false if processing fail
     * @throws PoolException if a database access error occurs
     */
    public boolean closeAllConnection() throws PoolException {
        boolean complete = false;
        try {
            for (Connection connection : connectionsBusy) {
                connection.close();
            }
            for (Connection connection : connectionsFree) {
                connection.close();
            }
            complete = true;
        } catch (SQLException ex) {
            throw new PoolException("Connection can not be closed", ex);
        }
        return complete;
    }

}
