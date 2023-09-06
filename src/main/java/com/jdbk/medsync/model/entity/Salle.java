package com.jdbk.medsync.model.entity;


import com.jdbk.medsync.model.Enum.Machine;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int etage;
    private String numeroSalle;

    private Machine machine;

    @OneToMany(mappedBy = "salle", cascade = CascadeType.ALL)
    private Set<RendezVous> rendezVous;
}