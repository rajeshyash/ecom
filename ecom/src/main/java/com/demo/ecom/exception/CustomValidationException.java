package com.demo.ecom.exception;

public class CustomValidationException extends IllegalArgumentException {

    public CustomValidationException(String message) {
        super(message);
    }
}
