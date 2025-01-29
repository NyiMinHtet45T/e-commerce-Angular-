package org.example.onlineshopbackend.exception;

import java.io.Serial;

public class JwtTokenInvalidateException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public JwtTokenInvalidateException(String message, Throwable cause) {
        super(message, cause);
    }
}
