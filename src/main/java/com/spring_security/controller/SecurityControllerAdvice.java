package com.spring_security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class SecurityControllerAdvice {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> accessDeniedExceptionHandler(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("Пошёл нахуй");
    }
}
