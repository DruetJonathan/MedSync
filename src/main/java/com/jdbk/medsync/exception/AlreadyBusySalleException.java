package com.jdbk.medsync.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AlreadyBusySalleException extends RuntimeException{

    private final Long salleId;
    private final LocalDateTime dateDebut;
    private final LocalDateTime dateFin;

    public AlreadyBusySalleException(Long salleId, LocalDateTime dateDebut, LocalDateTime dateFin) {
        super("Already exist rdv at this time for room {"+salleId+"}: " + dateDebut + "  " + dateFin);
        this.salleId = salleId;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

}
