package com.Client.Client.Exceptions;

public class CpfAlreadyExistsException extends RuntimeException {
    public CpfAlreadyExistsException(String message) {
        super(message);
    }
}
