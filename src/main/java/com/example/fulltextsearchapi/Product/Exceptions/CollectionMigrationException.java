package com.example.fulltextsearchapi.Product.Exceptions;

public class CollectionMigrationException extends RuntimeException {
    public CollectionMigrationException(String message) {
        super(message);
    }
    public CollectionMigrationException(String message, Throwable cause) {
        super(message, cause);
    }
}
