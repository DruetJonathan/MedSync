package com.jdbk.medsync.model.DTO;

import com.jdbk.medsync.model.entity.Demande;
import com.jdbk.medsync.model.entity.Produit;

import java.time.LocalDateTime;
import java.util.Set;

public class SimpleProduitDTO {

    private String libele;
    public static ProduitDTO toDTO(Produit produit){

        if( produit == null )
            return null;

        return ProduitDTO.builder()
                .libele(produit.getLibele())
                .build();
    }
}
