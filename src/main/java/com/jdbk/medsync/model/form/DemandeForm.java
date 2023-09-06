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
    private Long idUser;

    private Machine machine;
    @NotNull
    private Long idRendezVous;

    public Demande toEntity() {
        Demande demande = new Demande();
        demande.setProduits(this.produits);
        demande.setDuree(this.duree);
        demande.setRendezVous(null);
        // TODO verifier le nulml du rendez vous
        demande.setMachine(this.machine);

        return demande;
    }
}
