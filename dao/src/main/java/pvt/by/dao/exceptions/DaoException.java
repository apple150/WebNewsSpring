/**
 * User: yslabko
 * Date: 14.04.14
 * Time: 15:00
 * <p>
 * Copyright (c) 2012 by VeriFone, Inc.
 * All Rights Reserved.
 * <p>
 * THIS FILE CONTAINS PROPRIETARY AND CONFIDENTIAL INFORMATION
 * AND REMAINS THE UNPUBLISHED PROPERTY OF VERIFONE, INC.
 * <p>
 * Use, disclosure, or reproduction is prohibited
 * without prior written approval from VeriFone, Inc.
 */

package pvt.by.dao.exceptions;

public class DaoException extends Exception {
    private Exception exception;
    public DaoException(Exception exception) {
        this.exception = exception;
    }
    public Exception getException() {
        return exception;
    }
    public void setException(Exception exception) {
        this.exception = exception;
    }
}
