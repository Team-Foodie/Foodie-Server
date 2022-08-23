package com.foodie.foodie.exception;

public class InvalidCategoryTypeException extends RuntimeException{
    public InvalidCategoryTypeException(String message) { super(message); }

    public InvalidCategoryTypeException(String message, Throwable cause) { super(message, cause);}
}
