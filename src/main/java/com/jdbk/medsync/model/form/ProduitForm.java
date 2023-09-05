package com.jdbk.medsync.model.form;

import com.jdbk.medsync.model.entity.Produit;

import java.util.Date;

public class ProduitForm {


        private String libele;

        private Long quantite;

        private Date dateExpiration;

        public Produit toEntity() {
            Produit produit = new Produit();
            produit.setLibele(this.libele);
            produit.setQuantite(this.quantite);
            produit.setDateExpiration(this.dateExpiration);
            return produit;
        }
}
