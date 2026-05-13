package com.scout.cs2scout.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiErrorResponse> handleNotFound(
          ResourceNotFoundException ex,
          HttpServletRequest request) {

    ApiErrorResponse erro = new ApiErrorResponse(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            HttpStatus.NOT_FOUND.getReasonPhrase(),
            ex.getMessage(),
            request.getRequestURI(),
            null
    );

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiErrorResponse> handleValidationErrors(
          MethodArgumentNotValidException ex,
          HttpServletRequest request) {

    Map<String, String> fields = new HashMap<>();

    ex.getBindingResult().getFieldErrors().forEach(error -> {
      fields.put(error.getField(), error.getDefaultMessage());
    });

    ApiErrorResponse erro = new ApiErrorResponse(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            "Validation Error",
            "Campos inválidos",
            request.getRequestURI(),
            fields
    );

    return ResponseEntity.badRequest().body(erro);
  }
}