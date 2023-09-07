package com.jdbk.medsync.model.DTO;

import com.jdbk.medsync.model.entity.Demande;
import com.jdbk.medsync.model.entity.Produit;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Data
@Builder
public class ProduitDTO {

    private Long id;
    private String libele;
    private Long quantite;
    private LocalDateTime dateExpiration;
    private Set<Demande> demandes;

    public static ProduitDTO toDTO(Produit produit){

        if( produit == null )
            return null;

        return ProduitDTO.builder()
                .id(produit.getId())
                .libele(produit.getLibele())
                .dateExpiration(produit.getDateExpiration())
                .quantite(produit.getQuantite())
                .demandes(produit.getDemandes())
                .build();
    }
}
