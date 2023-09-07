package com.jdbk.medsync.exception;

public class NotTheGoodPasswordException extends RuntimeException{
    private final Long id;
    private final String email;
    public NotTheGoodPasswordException(Long id, String email) {
        super("Not good informations for user with id ->{"+id+"} & email->{"+email+"}");
        this.id = id;
        this.email = email;
    }


}
