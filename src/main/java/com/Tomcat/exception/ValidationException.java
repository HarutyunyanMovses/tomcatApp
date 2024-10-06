package com.Tomcat.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String key,String mess) {
        super(key+":"+mess);
    }
}
