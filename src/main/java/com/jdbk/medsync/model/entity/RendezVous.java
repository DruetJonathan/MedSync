package com.jdbk.medsync.model.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    Date dateDebut;
    Date dateFin;

    @OneToOne(mappedBy = "rendezVous")
    private Demande demande;
}
