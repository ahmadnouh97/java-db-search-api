package com.example.fulltextsearchapi.Product.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {CollectionAlreadyExistsException.class})
    public ResponseEntity<Object> handleCollectionAlreadyExists(CollectionAlreadyExistsException exception) {
        return new ResponseEntity<>(new ApiException(
                exception.getMessage(),
                HttpStatus.CONFLICT,
                ZonedDateTime.now(ZoneId.of("Z"))
        ), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {CollectionNotFoundException.class})
    public ResponseEntity<Object> handleCollectionNotFound(CollectionNotFoundException exception) {
        return new ResponseEntity<>(new ApiException(
                exception.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z"))
        ), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {CollectionMigrationException.class})
    public ResponseEntity<Object> handleCollectionMigration(CollectionMigrationException exception) {
        return new ResponseEntity<>(new ApiException(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        ), HttpStatus.BAD_REQUEST);
    }
}
