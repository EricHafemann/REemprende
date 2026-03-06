package org.example.exception;

public class PermissionInvalidException extends RuntimeException {
    public PermissionInvalidException(String message) {
        super(message);
    }
}
