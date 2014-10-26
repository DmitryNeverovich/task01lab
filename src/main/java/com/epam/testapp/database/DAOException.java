
package com.epam.testapp.database;

import com.epam.testapp.exception.ProjectException;

/**
 * Exception that provides information about error in Dao
 *
 */
public class DAOException extends ProjectException {

    private static final long serialVersionID = 1L;

    public DAOException() {
        super();
    }

    /**
     * DAOException using given message and given hidden exception
     *
     * @param msg The message explaining the reason for the exception
     */
    public DAOException(String msg) {
        super(msg);
    }

    /**
     * DAOException using given message
     *
     * @param msg The message explaining the reason for the exception
     * @param e exception that will be wrapped
     */
    public DAOException(String msg, Exception e) {
        super(msg, e);
    }

    /**
     * DAOException using given hidden exception.
     *
     * @param e throwable that will be wrapped
     */
    public DAOException(Throwable exception) {
        super(exception);
    }
}
