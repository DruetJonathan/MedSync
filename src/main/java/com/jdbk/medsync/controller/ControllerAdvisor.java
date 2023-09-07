package com.jdbk.medsync.controller;

import com.jdbk.medsync.exception.AlreadyBusySalleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(AlreadyBusySalleException.class)
    public ResponseEntity<String> handle(AlreadyBusySalleException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

}
