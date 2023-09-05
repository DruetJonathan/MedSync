package com.jdbk.medsync.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity @Getter @Setter
public class Produit {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libele;

    private Long quantite;

    private Date dateExpiration;

    @ManyToMany
    @JoinTable(
            name = "produit_demande",
            joinColumns = @JoinColumn(name = "produit_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name= "demande_id", nullable = false)
    )
    private Set<Demande> demandes;

}
