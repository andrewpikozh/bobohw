package org.example.exception;

public class OrmException extends RuntimeException {
    public OrmException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrmException(String message) {
        super(message);
    }
}
