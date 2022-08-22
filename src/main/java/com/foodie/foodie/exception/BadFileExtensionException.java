package com.foodie.foodie.exception;

public class BadFileExtensionException extends RuntimeException{

    public BadFileExtensionException(String message) { super(message); }

    public BadFileExtensionException(String message, Throwable cause) { super(message, cause);}
}
