package com.jdbk.medsync.controller;

import com.jdbk.medsync.model.DTO.ProduitDTO;
import com.jdbk.medsync.model.entity.Produit;
import com.jdbk.medsync.service.ProduitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/produit")
public class ProduitController {

    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

public ResponseEntity<ProduitDTO> getProduitById(@PathVariable Long id) {
    Produit produit = produitService.getProduitById(id);
   // ProduitDTO body = ProduitDTO.toDTO(produit);
    return null;
    }
}
