package com.jdbk.medsync.controller;

import com.jdbk.medsync.model.DTO.ProduitDTO;
import com.jdbk.medsync.model.entity.Produit;
import com.jdbk.medsync.model.form.ProduitForm;
import com.jdbk.medsync.service.notImpl.ProduitService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/produits")
public class ProduitController {

    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @PreAuthorize("hasAuthority('ADMINISTRATIF')")
    @PostMapping("/add")
    public ResponseEntity<Long> addProduit(@RequestBody @Valid ProduitForm form) {
        Produit entity = form.toEntity();
        Long idProduit = produitService.addProduit(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(idProduit);
    }

    @PreAuthorize("hasAuthority('ADMINISTRATIF')")
    @PutMapping("/{id:[0-9]+}")
    public ResponseEntity<ProduitDTO> updateProduit(@PathVariable Long id, @RequestBody @Valid ProduitForm form) {
        Produit entity = form.toEntity();
        Produit produit = produitService.updateProduit(id, entity);
        return ResponseEntity.status(HttpStatus.OK).body(ProduitDTO.toDTO(entity));
    }

    @PreAuthorize("hasAuthority('ADMINISTRATIF')")
    @DeleteMapping("/{id:[0-9]+}")
    public ResponseEntity<String> removeProduit(@PathVariable Long id) {
        Produit produit = produitService.removeProduit(id);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted");

    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRATIF','MEDECIN')")
    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<ProduitDTO> getProduitById(@PathVariable Long id) {
        Produit produit = produitService.getProduitById(id);
        ProduitDTO produitDTO = ProduitDTO.toDTO(produit);
        return ResponseEntity.status(HttpStatus.OK).body(produitDTO);
    }
    @PreAuthorize("hasAnyAuthority('ADMINISTRATIF','MEDECIN')")
    @GetMapping
    public ResponseEntity<List<ProduitDTO>> getAll() {
        return ResponseEntity.ok(
                produitService.getAll().stream()
                        .map(ProduitDTO::toDTO)
                        .toList()
        );
    }
    @PreAuthorize("hasAuthority('ADMINISTRATIF')")
    @PutMapping("/{id:[0-9]+}/updateStock")
    public ResponseEntity<ProduitDTO> updateStockProduit(@PathVariable Long id, @RequestParam Long nouvelleQuantite) {
            Produit produit = produitService.updateStockProduit(id, nouvelleQuantite);
            return ResponseEntity.status(HttpStatus.OK).body(ProduitDTO.toDTO(produit));
    }


}

