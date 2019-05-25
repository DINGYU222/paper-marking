package com.papermaking.exception;

public class PaperCreateErrorException extends RuntimeException {
    public PaperCreateErrorException() {
    }

    public PaperCreateErrorException(String message) {
        super(message);
    }
}
