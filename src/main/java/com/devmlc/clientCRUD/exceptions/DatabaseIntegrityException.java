package com.devmlc.clientCRUD.exceptions;

public class DatabaseIntegrityException extends RuntimeException {
    public DatabaseIntegrityException(String message) {
        super(message);
    }
}
