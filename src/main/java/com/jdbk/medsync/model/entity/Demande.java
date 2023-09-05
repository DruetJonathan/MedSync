package com.jdbk.medsync.model.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(mappedBy = "demandes")
    Set<Produit> produits;
}
