package org.alima.exception;


public class AlreadyExistException extends BaseAppException {
    public AlreadyExistException(String message, String errorCode) {
        super(message, errorCode);
    }
}
