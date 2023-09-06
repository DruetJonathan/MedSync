package com.jdbk.medsync.model.form;

import com.jdbk.medsync.model.entity.Produit;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class ProduitForm {

        @NotBlank
        private String libele;

        @NotBlank
        private Long quantite;

        @NotBlank @Future
        private Date dateExpiration;

        public Produit toEntity() {
            Produit produit = new Produit();
            produit.setLibele(this.libele);
            produit.setQuantite(this.quantite);
            produit.setDateExpiration(this.dateExpiration);
            return produit;
        }
}
