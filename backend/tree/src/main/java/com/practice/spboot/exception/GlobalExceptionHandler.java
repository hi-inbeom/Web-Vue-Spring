package com.practice.spboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

// RestController의 에러는 모두 RestControllerAdvice이 자동으로 호출이 됨
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserExceptions.class)
    public ResponseEntity<Map<String, String>> handleUserException(UserExceptions ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage());
        
        HttpStatus status = ex.getStatus();
        
        System.out.println("Hello Exceptions");
        
        // 클라이언트에 오류 메시지 반환
        return ResponseEntity.status(status).body(errorResponse);
    }
    
    // 유효성 검사 오류 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        
        // 유효성 검사 오류 메시지 처리
        ex.getBindingResult().getAllErrors().forEach(error -> {
            errorResponse.put("message", error.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
