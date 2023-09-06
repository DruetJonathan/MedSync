package com.jdbk.medsync.controller;

import com.jdbk.medsync.model.DTO.ProduitDTO;
import com.jdbk.medsync.model.entity.Produit;
import com.jdbk.medsync.service.ProduitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/produit")
public class ProduitController {

    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

public ResponseEntity<ProduitDTO> getProduitById(@PathVariable @RequestBody Long id) {
    Produit produit = produitService.getProduitById(id);
    ProduitDTO body = new ProduitDTO();
    body.toDTO(produit);
    return null;
    }
}
