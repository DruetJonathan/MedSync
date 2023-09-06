package com.jdbk.medsync.controller;

import com.jdbk.medsync.exception.AlreadyExistException;
import com.jdbk.medsync.exception.InvalidQuantityException;
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
@RequestMapping("/produits")
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

    @PutMapping("/{id:[0-9]+}")
    public ResponseEntity<?> updateProduit(@PathVariable Long id, @RequestBody @Valid ProduitForm form) {
        Produit entity = form.toEntity();
        try {
            Produit produit = produitService.updateProduit(id, entity);
            return ResponseEntity.status(HttpStatus.OK).body(produit);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }catch (InvalidQuantityException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid quantity");
        }
    }

    @DeleteMapping("/{id:[0-9]+}")
    public ResponseEntity<?> removeProduit(@PathVariable Long id) {
        try {
            Produit produit = produitService.removeProduit(id);
            return ResponseEntity.status(HttpStatus.OK).body("Product deleted");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
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

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(
                produitService.getAll().stream()
                        .map(ProduitDTO::toDTO)
                        .toList()
        );
    }

    @PutMapping("/{id:[0-9]+}/updateStock")
    public ResponseEntity<?> updateStockProduit(@PathVariable Long id, @RequestParam Long nouvelleQuantite) {
        try {
            Produit produit = produitService.updateStockProduit(id, nouvelleQuantite);
            return ResponseEntity.status(HttpStatus.OK).body(produit);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        } catch (InvalidQuantityException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid quantity");
        }
    }


}

