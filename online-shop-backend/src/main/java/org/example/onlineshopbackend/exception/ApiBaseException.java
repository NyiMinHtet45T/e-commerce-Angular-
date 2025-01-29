package org.example.onlineshopbackend.exception;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ApiBaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final List<String> errors = new ArrayList<>();

    public ApiBaseException(String message) {
        super(message);
        errors.add(message);
    }

    public ApiBaseException(String message, Throwable cause) {
        super(message, cause);
        errors.add(message);
    }

    public List<String> getErrors() {
        return errors;
    }
}
