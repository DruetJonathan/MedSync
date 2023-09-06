package com.jdbk.medsync.exception;

public class AlreadyBusySalleException extends RuntimeException{
    public AlreadyBusySalleException() {
    }

    public AlreadyBusySalleException(String message) {
        super(message);
    }
}
