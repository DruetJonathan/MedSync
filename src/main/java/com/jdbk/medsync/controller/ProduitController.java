package com.jdbk.medsync.controller;

import com.jdbk.medsync.exception.AlreadyExistException;
import com.jdbk.medsync.exception.NotFoundException;
import com.jdbk.medsync.model.DTO.ProduitDTO;
import com.jdbk.medsync.model.entity.Produit;
import com.jdbk.medsync.model.form.ProduitForm;
import com.jdbk.medsync.service.ProduitService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/produit")
public class ProduitController {

    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduit(@RequestBody @Valid ProduitForm form) {
        Produit entity = form.toEntity();
        try {
            Long idProduit = produitService.addProduit(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body(idProduit);
        } catch (AlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Already exist by name: " + entity.getLibele());
        }
    }


    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<?> getProduitById(@PathVariable Long id) {
        try {
            Produit produit = produitService.getProduitById(id);
            ProduitDTO produitDTO = ProduitDTO.toDTO(produit);
            return ResponseEntity.status(HttpStatus.OK).body(produitDTO);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }




}

