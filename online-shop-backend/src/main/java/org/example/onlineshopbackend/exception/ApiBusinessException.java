package org.example.onlineshopbackend.exception;

public class ApiBusinessException extends ApiBaseException{

    public ApiBusinessException(String message) {
        super(message);
    }

    public ApiBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
