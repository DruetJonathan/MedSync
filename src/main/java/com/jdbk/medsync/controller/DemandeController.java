package com.jdbk.medsync.controller;

import com.jdbk.medsync.model.DTO.DemandeDTO;
import com.jdbk.medsync.model.entity.Demande;
import com.jdbk.medsync.model.entity.Produit;
import com.jdbk.medsync.model.form.DemandeForm;
import com.jdbk.medsync.service.notImpl.*;
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
    public ResponseEntity<Long> addDemande( @RequestBody @Valid DemandeForm form) {
        Demande entity = form.toEntity();
        List<Produit> produits = produitService.getAllById(form.getProduitIds());
        entity.setProduits( new HashSet<>(produits));
        entity.setDemandeur(userService.getOne(form.getDemandeur()));
        Long idDemande = demandeService.addDemande(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(idDemande);
    }

    @PutMapping("/{id:[0-9]+}")
    public ResponseEntity<DemandeDTO> updateDemande(@PathVariable Long id, @RequestBody @Valid DemandeForm form) {
        Demande entity = form.toEntity();
            Demande demande = demandeService.updateDemande(id, entity);
            demande.setDemandeur(userService.getOne(form.getDemandeur()));
            return ResponseEntity.status(HttpStatus.OK).body(DemandeDTO.toDTO(demande));
    }

    @DeleteMapping("/{id:[0-9]+}")
    public ResponseEntity<String> removeDemande(@PathVariable Long id) {
            Demande demande = demandeService.removeDemande(id);
            return ResponseEntity.status(HttpStatus.OK).body("Demand deleted");
    }

    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<DemandeDTO> getOne(@PathVariable Long id) {
            Demande demande = demandeService.getOne(id);
            return ResponseEntity.status(HttpStatus.OK).body(DemandeDTO.toDTO(demande));

    }

    @GetMapping
    public ResponseEntity<List<DemandeDTO>> getAll() {
        return ResponseEntity.ok(
                demandeService.getAll().stream()
                        .map(DemandeDTO::toDTO)
                        .toList()
        );
    }
}
