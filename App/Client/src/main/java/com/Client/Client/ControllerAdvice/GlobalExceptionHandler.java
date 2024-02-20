package com.Client.Client.ControllerAdvice;

import com.Client.Client.Dto.CustomErrorResponse;
import com.Client.Client.Exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCepException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomErrorResponse> handleInvalidCepExceptionCep(InvalidCepException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(400, ex.getMessage(), "Enter a valid cep code!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(CpfAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomErrorResponse> handleInvalidCepExceptionCpf(CpfAlreadyExistsException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(400, ex.getMessage(), "Try other Cpf!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(IdNotExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomErrorResponse> handleInvalidCepExceptionId(IdNotExistsException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(400, ex.getMessage(), "Try id valid!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(NameDishNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomErrorResponse> handleInvalidCepExceptionName(NameDishNotFoundException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(400, ex.getMessage(), "Check the restaurant menu");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(CpfAlreadyFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomErrorResponse> handleInvalidCepExceptionCpfFormat(CpfAlreadyFormatException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(400, ex.getMessage(), "Enter a valid CPF");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomErrorResponse> handleInvalidCepExceptionEmail(EmailAlreadyExistsException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(400, ex.getMessage(), "Enter other email");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}