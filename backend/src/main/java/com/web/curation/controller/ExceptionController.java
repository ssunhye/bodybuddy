package com.web.curation.controller;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionController {
    @ExceptionHandler({ExpiredJwtException.class})
    public ResponseEntity handleExpiredJwtException(final ExpiredJwtException e)
    {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("expired");
    }
}