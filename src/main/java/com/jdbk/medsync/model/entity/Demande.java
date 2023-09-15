package com.jdbk.medsync.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @ManyToMany(mappedBy = "demandes",cascade = {CascadeType.ALL})
    @JsonIgnore

    Set<Produit> produits;

    private Long duree;

    @ManyToOne(optional = false)
    @JoinColumn(name = "demandeur_id", referencedColumnName = "id")
    @JsonBackReference
    private User demandeur;

    @Enumerated(EnumType.STRING)
    public Machine machine;

    @OneToOne(mappedBy = "demande")
    @JsonIgnore
    private RendezVous rendezVous;
}
