package com.jdbk.medsync.model.form;

import com.jdbk.medsync.model.entity.Produit;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProduitFormModif {
    @NotNull

    private Long id;
        @NotBlank
        private String libele;

        @NotNull
        private Long quantite;

        @NotNull
        @Future
        private LocalDateTime dateExpiration;

        public Produit toEntity() {
            Produit produit = new Produit();
            produit.setId(this.id);
            produit.setLibele(this.libele);
            produit.setQuantite(this.quantite);
            produit.setDateExpiration(this.dateExpiration);
            return produit;
        }
}
