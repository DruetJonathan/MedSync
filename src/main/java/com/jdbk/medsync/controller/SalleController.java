package com.jdbk.medsync.controller;

import com.jdbk.medsync.exception.AlreadyExistException;
import com.jdbk.medsync.exception.InvalidQuantityException;
import com.jdbk.medsync.exception.NotFoundException;
import com.jdbk.medsync.model.DTO.ProduitDTO;
import com.jdbk.medsync.model.DTO.SalleDTO;
import com.jdbk.medsync.model.entity.Produit;
import com.jdbk.medsync.model.entity.Salle;
import com.jdbk.medsync.model.form.ProduitForm;
import com.jdbk.medsync.model.form.SalleForm;
import com.jdbk.medsync.service.SalleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/salles")
public class SalleController {
    private final SalleService salleService;

    public SalleController(SalleService salleService) {
        this.salleService = salleService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSalle(@RequestBody @Valid SalleForm form) {
        Salle entity = form.toEntity();
        try {
            Long idSalle = salleService.addSalle(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body(idSalle);
        } catch (AlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Salle with etage and number of salle already exist");
        }
    }

    @PutMapping("/{id:[0-9]+}")
    public ResponseEntity<?> updateSalle(@PathVariable Long id, @RequestBody @Valid SalleForm form) {
        Salle entity = form.toEntity();
        try {
            Salle salle = salleService.updateSalle(id, entity);
            return ResponseEntity.status(HttpStatus.OK).body(salle);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Salle not found");
        }
    }

    @DeleteMapping("/{id:[0-9]+}")
    public ResponseEntity<?> removeSalle(@PathVariable Long id) {
        try {
            Salle salle = salleService.removeSalle(id);
            return ResponseEntity.status(HttpStatus.OK).body("Salle deleted");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Salle not found");
        }
    }



    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<?> getSalleById(@PathVariable Long id) {
        try {
            Salle salle = salleService.getOne(id);
            SalleDTO salleDTO = SalleDTO.toDTO(salle);
            return ResponseEntity.status(HttpStatus.OK).body(salle);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Salle not found");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(
                salleService.getAll().stream()
                        .map(SalleDTO::toDTO)
                        .toList()
        );
    }

}
