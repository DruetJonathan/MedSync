package com.jdbk.medsync.model.form;

import com.jdbk.medsync.model.Enum.Machine;
import com.jdbk.medsync.model.entity.Produit;
import com.jdbk.medsync.model.entity.RendezVous;
import com.jdbk.medsync.model.entity.Salle;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

public class SalleForm {
    private Long id;

    private int etage;
    private String numeroSalle;
    private Machine machine;
    private Set<RendezVous> rendezVous = new HashSet<>();
    public Salle toEntity() {
        Salle salle = new Salle();
        salle.setEtage(etage);
        salle.setNumeroSalle(numeroSalle);
        salle.setMachine(machine);
        salle.setRendezVous(rendezVous);
        return salle;
    }
}
