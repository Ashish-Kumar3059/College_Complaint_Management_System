package com.example.demo.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex) {
        Map<String, Object> res = new HashMap<>();
        res.put("message", ex.getMessage());
        res.put("status", 404);
        res.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, Object>> handleDenied(AccessDeniedException ex) {
        Map<String, Object> res = new HashMap<>();
        res.put("message", ex.getMessage());
        res.put("status", 403);
        res.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(res, HttpStatus.FORBIDDEN);
    }
}
