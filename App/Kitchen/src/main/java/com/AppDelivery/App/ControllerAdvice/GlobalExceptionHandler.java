package com.AppDelivery.App.ControllerAdvice;

import com.AppDelivery.App.Dto.CustomErrorResponse;
import com.AppDelivery.App.Exceptions.MenuIncorrectException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MenuIncorrectException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomErrorResponse> handleInvalidCepExceptionCep(MenuIncorrectException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(400, ex.getMessage(), "Enter Id valid!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
