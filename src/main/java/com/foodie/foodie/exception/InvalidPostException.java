package com.foodie.foodie.exception;

public class InvalidPostException extends RuntimeException {
    public InvalidPostException(String message) { super(message); }

    public InvalidPostException(String message, Throwable cause) { super(message, cause);}
}
