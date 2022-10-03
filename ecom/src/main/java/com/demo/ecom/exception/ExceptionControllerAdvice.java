package com.demo.ecom.exception;

import com.demo.ecom.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<ApiResponse> handleCustomValidationException(CustomValidationException ex){
        String message = ex.getMessage();
        return new ResponseEntity<ApiResponse>(new ApiResponse(false, message), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationException(
            MethodArgumentNotValidException ex) {
        StringBuilder errorInfo= new StringBuilder("");
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorInfo.append(errorMessage).append(" ");
            System.out.println("Error "+errorMessage);
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<ApiResponse>(new ApiResponse(false, errorInfo.toString()), HttpStatus.BAD_REQUEST);
    }

}
