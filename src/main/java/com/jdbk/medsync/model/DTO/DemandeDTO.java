package com.jdbk.medsync.model.DTO;

import com.jdbk.medsync.model.Enum.Machine;
import com.jdbk.medsync.model.entity.Demande;
import com.jdbk.medsync.model.entity.Produit;
import com.jdbk.medsync.model.entity.RendezVous;
import com.jdbk.medsync.model.entity.User;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class DemandeDTO {

    private Long id;
    private Set<ProduitDTO> produits;
    private Long duree;
    private UserDTO demandeur;
    private String machine;
    private RendezVousDTO rendezVous;

    public static DemandeDTO toDTO(Demande demande){

        if( demande == null )
            return null;

        return DemandeDTO.builder()
                .id(demande.getId())
//                .produits(demande.getProduits())
                .produits(demande.getProduits().stream().map(ProduitDTO::toDTO).collect(Collectors.toSet()))
                .duree(demande.getDuree())
                .demandeur(UserDTO.toDTO(demande.getDemandeur()))
                .machine(demande.getMachine().getValue())
                .rendezVous(RendezVousDTO.toDTO(demande.getRendezVous()))
                .build();
    }
}
