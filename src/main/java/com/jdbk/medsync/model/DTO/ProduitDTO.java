package com.jdbk.medsync.model.DTO;

import com.jdbk.medsync.model.entity.Demande;
import com.jdbk.medsync.model.entity.Produit;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class ProduitDTO {

    private Long id;
    private String libele;
    private Long quantite;
    private Date dateExpiration;
    private Set<Demande> demandes;

    public ProduitDTO toDTO(Produit produit){
        ProduitDTO produit = new ProduitDTO();
        produit.setId(id);
        produit.setLibele(libele);
        produit.setDateExpiration(dateExpiration);
        produit.setDemandes(demandes);
        return produit;
    }
}
