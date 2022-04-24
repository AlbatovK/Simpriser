package com.albatros.simspriser.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.concurrent.ExecutionException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {ExecutionException.class, InterruptedException.class})
    protected ResponseEntity<Object> resolveInternalStorageException(Exception ex, WebRequest request) {
        return ResponseEntity.internalServerError().build();
    }
}
