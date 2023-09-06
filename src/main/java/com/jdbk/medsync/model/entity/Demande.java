package com.jdbk.medsync.model.entity;

import com.jdbk.medsync.model.Enum.Machine;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
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
}
