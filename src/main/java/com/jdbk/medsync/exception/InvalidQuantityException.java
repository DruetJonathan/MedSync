package com.jdbk.medsync.exception;

public class InvalidQuantityException extends RuntimeException{

    private final Long id;
    public InvalidQuantityException(Long id) {
        super("Invalid quantity for product at id ->{"+id+"}");
        this.id = id;
    }

}
