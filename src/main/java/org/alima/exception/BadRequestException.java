package org.alima.exception;



public class BadRequestException extends BaseAppException {
    public BadRequestException(String message, String errorCode) {
        super(message, errorCode);
    }
}
