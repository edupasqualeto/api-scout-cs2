package com.scout.cs2scout.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErroResponse> handleNotFound(ResourceNotFoundException ex) {

    ErroResponse erro = new ErroResponse(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage()
    );

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
  }
}