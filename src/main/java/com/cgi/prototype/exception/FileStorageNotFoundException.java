package com.cgi.prototype.exception;

/**
 * @author aralco
 */
public class FileStorageNotFoundException extends RuntimeException {

    public FileStorageNotFoundException(String message) {
        super(message);
    }

    public FileStorageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
