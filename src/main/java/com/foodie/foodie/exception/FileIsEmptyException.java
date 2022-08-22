package com.foodie.foodie.exception;

public class FileIsEmptyException extends RuntimeException{

    public FileIsEmptyException(String message) { super(message); }

    public FileIsEmptyException(String message, Throwable cause) { super(message, cause);}
}
