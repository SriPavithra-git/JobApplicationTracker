package com.Application.JobTracker.exception;

import com.Application.JobTracker.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleResourceNotFound(ResourceNotFoundException ex){
        ApiResponse<Object> response=ApiResponse.builder()
                .Success(false)
                .message(ex.getMessage())
                .data(null)
                .build();
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(Exception ex){
        ApiResponse<Object> response=ApiResponse.builder()
                .Success(false)
                .message(ex.getMessage()+"something went wrong")
                .data(null)
                .build();
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
