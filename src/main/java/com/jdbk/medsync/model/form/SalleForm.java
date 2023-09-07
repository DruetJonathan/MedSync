package com.jdbk.medsync.model.form;

import com.jdbk.medsync.model.Enum.Machine;
import com.jdbk.medsync.model.entity.Produit;
import com.jdbk.medsync.model.entity.RendezVous;
import com.jdbk.medsync.model.entity.Salle;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Data

public class SalleForm {

    @NotNull
    private Integer etage;
    @NotNull
    private String numeroSalle;
    @NotNull
    private Machine machine;
    private Set<Long> rendezVous = new HashSet<>();
    public Salle toEntity() {
        Salle salle = new Salle();
        salle.setEtage(etage);
        salle.setNumeroSalle(numeroSalle);
        salle.setMachine(machine);
        return salle;
    }
}
