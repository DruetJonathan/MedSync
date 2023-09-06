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

    private Set<Produit> produits;
    @NotNull
    private Long duree;
    @NotNull
    private User user;

    private Machine machine;
    @NotNull
    private RendezVous rendezVous;

    public Demande toEntity() {
        Demande demande = new Demande();
        demande.setProduits(this.produits);
        demande.setDuree(this.duree);
        demande.setUser(this.user);
        demande.setMachine(this.machine);
        demande.setRendezVous(this.rendezVous);
        return demande;
    }
}
