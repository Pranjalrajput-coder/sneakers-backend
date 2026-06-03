package com.sneaker.backend.error;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Error {

    private String message;
    private HttpStatus code;

    public Error(String message, HttpStatus code) {
        this.message = message;
        this.code = code;
    }
}
