package com.jdbk.medsync.mapper;

import com.jdbk.medsync.model.entity.Demande;
import com.jdbk.medsync.model.entity.Produit;
import com.jdbk.medsync.model.form.DemandeForm;
import com.jdbk.medsync.service.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@Component
public class DemandeMapper {

    private final UserService userService;
    private final ProduitService produitService;

    public DemandeMapper(UserService userService, ProduitService produitService) {
        this.userService = userService;
        this.produitService = produitService;
    }

    public Demande toEntity(DemandeForm form){
        if( form == null){
            return null;
        }

        Demande demande = new Demande();
        demande.setDuree(form.getDuree());
        demande.setMachine(form.getMachine());

        List<Produit> produits = produitService.getAllById(form.getProduitIds());
        demande.setProduits( new HashSet<>(produits));

        demande.setUser(userService.getOne(form.getIdUser()));

        return demande;
    }

}
