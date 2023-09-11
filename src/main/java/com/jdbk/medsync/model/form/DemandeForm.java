package com.jdbk.medsync.model.form;

import com.jdbk.medsync.model.Enum.Machine;
import com.jdbk.medsync.model.entity.Demande;
import com.jdbk.medsync.model.entity.Produit;
import com.jdbk.medsync.model.entity.RendezVous;
import com.jdbk.medsync.model.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class DemandeForm {

    @NotNull
    private Set<Long> produitIds;
    @NotNull
    private Long duree;
    @NotNull
    private Long demandeur;

    private String machine;

    public Demande toEntity() {
        Demande demande = new Demande();
        demande.setDuree(this.duree);
        demande.setMachine(Machine.fromString(machine));
        return demande;
    }
}
