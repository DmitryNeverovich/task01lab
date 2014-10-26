package com.epam.testapp.service;

import com.epam.testapp.database.*;
import com.epam.testapp.exception.ProjectException;

/**
 * Exception that provides information about error in Service
 *
 */
public class ServiceException extends ProjectException {

    private static final long serialVersionID = 1L;

    public ServiceException() {
        super();
    }

    /**
     * ServiceException using given message and given hidden exception
     *
     * @param msg The message explaining the reason for the exception
     */
    public ServiceException(String msg) {
        super(msg);
    }

    /**
     * ServiceException using given message
     *
     * @param msg The message explaining the reason for the exception
     * @param e exception that will be wrapped
     */
    public ServiceException(String msg, Exception e) {
        super(msg, e);
    }

    /**
     * ServiceException using given hidden exception.
     *
     * @param e throwable that will be wrapped
     */
    public ServiceException(Throwable exception) {
        super(exception);
    }
}
