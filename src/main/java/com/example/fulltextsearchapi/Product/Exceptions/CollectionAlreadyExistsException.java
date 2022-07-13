package com.example.fulltextsearchapi.Product.Exceptions;

public class CollectionAlreadyExistsException extends RuntimeException {
    public CollectionAlreadyExistsException(String message) {
        super(message);
    }
    public CollectionAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
