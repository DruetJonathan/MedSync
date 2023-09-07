package com.jdbk.medsync.exception;

public class AlreadyExistException extends RuntimeException {
    private final  Long id;
    private final  String className;
    public AlreadyExistException(Long id, String className) {
        super("In {"+className+"} entity not found at id -> {"+id+"}");
        this.id = id;
        this.className = className;

    }
}
