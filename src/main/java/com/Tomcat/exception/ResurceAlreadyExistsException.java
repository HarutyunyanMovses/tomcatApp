package com.Tomcat.exception;

public class ResurceAlreadyExistsException extends RuntimeException {
    public ResurceAlreadyExistsException(String key ,String message) {
        super(key + ": " + message);
    }
}
