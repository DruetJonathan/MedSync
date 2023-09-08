package com.jdbk.medsync.controller;

import com.jdbk.medsync.model.DTO.DemandeDTO;
import com.jdbk.medsync.model.entity.Demande;
import com.jdbk.medsync.model.entity.Produit;
import com.jdbk.medsync.model.entity.User;
import com.jdbk.medsync.model.form.DemandeForm;
import com.jdbk.medsync.service.notImpl.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('MEDECIN')")
    @PostMapping("/add")
    public ResponseEntity<Long> addDemande( @RequestBody @Valid DemandeForm form) {
        Demande entity = form.toEntity();
        List<Produit> produits = produitService.getAllById(form.getProduitIds());
        entity.setProduits( new HashSet<>(produits));
        entity.setDemandeur(userService.getOne(form.getDemandeur()));
        Long idDemande = demandeService.addDemande(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(idDemande);
    }
    //todo a modifier l'acces => juste pour test
    @PreAuthorize("hasAuthority('ADMINISTRATIF')")
    @PutMapping("/{id:[0-9]+}")
    public ResponseEntity<DemandeDTO> updateDemande(@PathVariable Long id, @RequestBody @Valid DemandeForm form) {
        Demande entity = form.toEntity();
        entity.setDemandeur(userService.getOne(form.getDemandeur()));
        entity.setProduits(new HashSet<>(produitService.getAllById(form.getProduitIds())));

        Demande demande = demandeService.updateDemande(id, entity);
//            demande.setDemandeur());
            return ResponseEntity.status(HttpStatus.OK).body(DemandeDTO.toDTO(demande));
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRATIF','MEDECIN')")
    @DeleteMapping("/{id:[0-9]+}")
    public ResponseEntity<String> removeDemande(@PathVariable Long id) {
            Demande demande = demandeService.removeDemande(id);
            return ResponseEntity.status(HttpStatus.OK).body("Demand deleted");
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRATIF','MEDECIN')")
    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<DemandeDTO> getOne(@PathVariable Long id) {
            Demande demande = demandeService.getOne(id);
            return ResponseEntity.status(HttpStatus.OK).body(DemandeDTO.toDTO(demande));

    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRATIF','MEDECIN')")
    @GetMapping
    public ResponseEntity<List<DemandeDTO>> getAll() {
        return ResponseEntity.ok(
                demandeService.getAll().stream()
                        .map(DemandeDTO::toDTO)
                        .toList()
        );
    }
//    @PreAuthorize("hasAnyRole('ADMINISTRATIF','MEDECIN')")
    @PreAuthorize("hasAuthority('MEDECIN')")
    @GetMapping("/specificDemande/user/{id:[0-9]+}")
    public ResponseEntity<List<DemandeDTO>> getAllDemandeForDemandeur(@PathVariable("id") Long idUser) {
        User user = userService.getOne(idUser);
        return ResponseEntity.ok(
                demandeService.getAllDemandeForDemandeur(user).stream()
                        .map(DemandeDTO::toDTO)
                        .toList()
        );
    }
}
