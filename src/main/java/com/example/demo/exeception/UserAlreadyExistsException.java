package com.example.demo.exeception;

/**
 * @author Sampson Alfred
 */

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
