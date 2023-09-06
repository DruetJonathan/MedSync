package com.jdbk.medsync.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateDebut;
    private Date dateFin;

    @OneToOne(mappedBy = "rendezVous")
    private Demande demande;

    @ManyToOne
    @JoinColumn(name = "user_id") // Nom de la colonne de jointure
    private User user;

    @ManyToOne
    @JoinColumn(name = "salle_id") // Nom de la colonne de jointure
    private Salle salle;
}
