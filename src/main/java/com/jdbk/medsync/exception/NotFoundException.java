package com.jdbk.medsync.exception;

public class NotFoundException extends RuntimeException{
    private final  Long id;
    private final  String className;
    public NotFoundException(Long id, String className) {
        super("In {"+className+"} entity not found at id -> {"+id+"}");
        this.id = id;
        this.className = className;

    }


}
