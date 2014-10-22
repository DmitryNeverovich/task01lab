/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.testapp.exception;

/**
 * Basic Exception that provides information about error in this project
 *
 */
public class ProjectException extends Exception {

    private static final long serialVersonUID = 1L;
    private Exception hiddenException;

    public ProjectException() {
        super();
    }

    /**
     * Constructs ProjectException with given message
     *
     * @param msg explaining the reason for the exception
     */
    public ProjectException(String msg) {
        super(msg);
    }

    /**
     * Constructs ProjectException with given message and hiddenException
     *
     * @param msg explaining the reason for the exception
     * @param e
     */
    public ProjectException(String msg, Exception e) {
        super(msg);
        hiddenException = e;
    }

    /**
     * ProjectException using given hidden exception.
     *
     * @param e throwable that will be wrapped
     */
    public ProjectException(Throwable exception) {
        super(exception);
    }

    /**
     * Return wrapped exception
     *
     * @return wrapped exception
     */
    public Exception getHiddenException() {
        return hiddenException;
    }

}
