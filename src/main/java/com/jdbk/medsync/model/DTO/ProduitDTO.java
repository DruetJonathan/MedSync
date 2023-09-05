package com.jdbk.medsync.model.DTO;

import com.jdbk.medsync.model.entity.Produit;

import java.util.Date;

public class ProduitDTO {

    private Long id;
    private String libele;
    private Long quantite;
    private Date dateExpiration;

    public ProduitDTO(Long id, String libele, Long quantite, Date dateExpiration) {
        this.id = id;
        this.libele = libele;
        this.quantite = quantite;
        this.dateExpiration = dateExpiration;
    }

    public static ProduitDTO fromEntity(Produit produit) {
        return new ProduitDTO(produit.getId(), produit.getLibele(), produit.getQuantite(), produit.getDateExpiration());
    }
}
