package ru.kaznacheev.restapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.kaznacheev.restapi.util.error.ErrorResponse;
import ru.kaznacheev.restapi.util.error.FilePathNotFoundException;
import ru.kaznacheev.restapi.util.error.OperationNotFoundException;
import ru.kaznacheev.restapi.util.error.UserFileNotFoundException;

import java.sql.Timestamp;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(OperationNotFoundException exception) {
        ErrorResponse response = new ErrorResponse("Operation not found", new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(FilePathNotFoundException exception) {
        ErrorResponse response = new ErrorResponse("File path not found", new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(UserFileNotFoundException exception) {
        ErrorResponse response = new ErrorResponse("File not found", new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
