package com.example.fulltextsearchapi.Product.Exceptions;

public class CollectionNotFoundException extends RuntimeException {
    public CollectionNotFoundException(String message) {
        super(message);
    }
    public CollectionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
