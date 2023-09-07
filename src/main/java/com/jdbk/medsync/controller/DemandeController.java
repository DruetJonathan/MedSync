package com.jdbk.medsync.controller;

import com.jdbk.medsync.exception.NotFoundException;
import com.jdbk.medsync.model.entity.Demande;
import com.jdbk.medsync.model.entity.Produit;
import com.jdbk.medsync.model.form.DemandeForm;
import com.jdbk.medsync.service.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/demandes")
public class DemandeController {

    private final RendezVousService rendezVousService;
    private final SalleService salleService;
    private final DemandeService demandeService;
    private final UserService userService;
    private final ProduitService produitService;

    public DemandeController(RendezVousService rendezVousService, SalleService salleService, DemandeService demandeService, UserService userService, ProduitService produitService) {
        this.rendezVousService = rendezVousService;
        this.salleService = salleService;
        this.demandeService = demandeService;
        this.userService = userService;
        this.produitService = produitService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addDemande( @RequestBody @Valid DemandeForm form) {
        // todo set user demande et salle id
        Demande entity = form.toEntity();

        List<Produit> produits = produitService.getAllById(form.getProduitIds());
        entity.setProduits( new HashSet<>(produits));

        entity.setUser(userService.getOne(form.getIdUser()));



        Long idDemande = demandeService.addDemande(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(idDemande);

    }

    @PutMapping("/{id:[0-9]+}")
    public ResponseEntity<?> updateDemande(@PathVariable Long id, @RequestBody @Valid DemandeForm form) {
        Demande entity = form.toEntity();
        try {
            Demande demande = demandeService.updateDemande(id, entity);
            return ResponseEntity.status(HttpStatus.OK).body(entity);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Demand not found");
        }
    }

    @DeleteMapping("/{id:[0-9]+}")
    public ResponseEntity<?> removeDemande(@PathVariable Long id) {
        try {
            Demande demande = demandeService.removeDemande(id);
            return ResponseEntity.status(HttpStatus.OK).body("Demand deleted");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Demand not found");
        }
    }

    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try {
            Demande demande = demandeService.getOne(id);
            return ResponseEntity.status(HttpStatus.OK).body(demande);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Demand not found");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(demandeService.getAll());
    }
}
