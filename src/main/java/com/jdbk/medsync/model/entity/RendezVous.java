package com.jdbk.medsync.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    @OneToOne
    @JoinColumn(name = "rendezvous_id")
    @JsonBackReference
    private Demande demande;

    @ManyToOne
    @JoinColumn(name = "createur_id") // Nom de la colonne de jointure
    @JsonBackReference

    private User creePar;

    @ManyToOne
    @JoinColumn(name = "salle_id") // Nom de la colonne de jointure
    @JsonBackReference

    private Salle salle;
}
