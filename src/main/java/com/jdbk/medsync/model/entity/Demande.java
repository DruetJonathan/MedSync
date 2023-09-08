package com.jdbk.medsync.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jdbk.medsync.model.Enum.Machine;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity @Getter @Setter
public class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(mappedBy = "demandes")
    Set<Produit> produits;

    private Long duree;

    @ManyToOne(optional = false)
    @JoinColumn(name = "demandeur_id", referencedColumnName = "id")
    @JsonBackReference
    private User demandeur;

    public Machine machine;

    @OneToOne(mappedBy = "demande")
    private RendezVous rendezVous;
}
