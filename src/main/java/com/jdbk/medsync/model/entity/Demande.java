package com.jdbk.medsync.model.entity;

import com.jdbk.medsync.model.Enum.Machine;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
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
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Machine machine;

    @OneToOne
    @JoinColumn(name = "rendezvous_id", referencedColumnName = "id")
    private RendezVous rendezVous;
}
