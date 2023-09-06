package com.jdbk.medsync.controller;

import com.jdbk.medsync.exception.AlreadyExistException;
import com.jdbk.medsync.exception.NotFoundException;
import com.jdbk.medsync.model.entity.Demande;
import com.jdbk.medsync.model.form.DemandeForm;
import com.jdbk.medsync.service.DemandeService;
import com.jdbk.medsync.service.RendezVousService;
import com.jdbk.medsync.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/demandes")
public class DemandeController {

    private final RendezVousService rendezVousService;
    private final SalleService salleService;
    private final DemandeService demandeService;
    private final UserService userService;

    public DemandeController(RendezVousService rendezVousService, DemandeService demandeService, UserService userService) {
        this.rendezVousService = rendezVousService;
        this.demandeService = demandeService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addDemande( @RequestBody @Valid DemandeForm form) {
        // todo set user demande et salle id
        Demande entity = form.toEntity();

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
