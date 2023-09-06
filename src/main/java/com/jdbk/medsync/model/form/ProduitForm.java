package com.jdbk.medsync.model.form;

import com.jdbk.medsync.model.entity.Produit;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ProduitForm {

        @NotBlank
        private String libele;

        @NotNull
        private Long quantite;

        @NotNull
        @Future
        private Date dateExpiration;

        public Produit toEntity() {
            Produit produit = new Produit();
            produit.setLibele(this.libele);
            produit.setQuantite(this.quantite);
            produit.setDateExpiration(this.dateExpiration);
            return produit;
        }
}
