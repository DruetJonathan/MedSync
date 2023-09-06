package com.jdbk.medsync.exception;

public class NotTheGoodPasswordException extends RuntimeException{
    public NotTheGoodPasswordException() {
    }

    public NotTheGoodPasswordException(String message) {
        super(message);
    }
}
