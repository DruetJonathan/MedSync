package com.jdbk.medsync.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity @Getter @Setter
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produit_id", nullable = false)
    private Long id;

    @Column(name = "produit_libele", nullable = false, unique = true)
    private String libele;

    @Column(name = "produit_quantite", nullable = false)
    private Long quantite;

    @Column(name = "produit_date_expiration", nullable = false)
    private LocalDateTime dateExpiration;

    @ManyToMany
    @JoinTable(
            name = "produit_demande",
            joinColumns = @JoinColumn(name = "produit_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name= "demande_id", nullable = false)
    )
    private Set<Demande> demandes = new HashSet<>();

}
