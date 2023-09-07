package com.jdbk.medsync.model.DTO;

import com.jdbk.medsync.model.Enum.Machine;
import com.jdbk.medsync.model.entity.Demande;
import com.jdbk.medsync.model.entity.Produit;
import com.jdbk.medsync.model.entity.RendezVous;
import com.jdbk.medsync.model.entity.User;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class DemandeDTO {

    private Long id;
    private Set<Produit> produits;
    private Long duree;
    private User demandeur;
    private Machine machine;
    private RendezVous rendezVous;

    public static DemandeDTO toDTO(Demande demande){

        if( demande == null )
            return null;

        return DemandeDTO.builder()
                .id(demande.getId())
                .produits(demande.getProduits())
                .duree(demande.getDuree())
                .demandeur(demande.getDemandeur())
                .machine(demande.getMachine())
                .rendezVous(demande.getRendezVous())
                .build();
    }
}
