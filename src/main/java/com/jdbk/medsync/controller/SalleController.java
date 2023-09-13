package com.jdbk.medsync.controller;

import com.jdbk.medsync.model.DTO.SalleDTO;
import com.jdbk.medsync.model.Enum.Machine;
import com.jdbk.medsync.model.entity.Salle;
import com.jdbk.medsync.model.form.SalleForm;
import com.jdbk.medsync.service.notImpl.RendezVousService;
import com.jdbk.medsync.service.notImpl.SalleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import java.util.HashSet;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/salles")
public class SalleController {
    private final SalleService salleService;
    private final RendezVousService rendezVousService;

    public SalleController(SalleService salleService, RendezVousService rendezVousService) {
        this.salleService = salleService;
        this.rendezVousService = rendezVousService;
    }
    @PreAuthorize("hasAuthority('ADMINISTRATIF')")

    @PostMapping("/add")
    public ResponseEntity<Long> addSalle(@RequestBody @Valid SalleForm form) {
        Salle entity = form.toEntity();
        Long idSalle = salleService.addSalle(entity);
        form.setRendezVous(new HashSet<>());
        return ResponseEntity.status(HttpStatus.CREATED).body(idSalle);
    }
    @PreAuthorize("hasAuthority('ADMINISTRATIF')")

    @PutMapping("/{id:[0-9]+}")
    public ResponseEntity<SalleDTO> updateSalle(@PathVariable Long id, @RequestBody @Valid SalleForm form) {
        Salle entity = form.toEntity();
            Salle salle = salleService.updateSalle(id, entity);
            return ResponseEntity.status(HttpStatus.OK).body(SalleDTO.toDTO(salle));

    }
    @PreAuthorize("hasAuthority('ADMINISTRATIF')")

    @DeleteMapping("/{id:[0-9]+}")
    public ResponseEntity<String> removeSalle(@PathVariable Long id) {
            Salle salle = salleService.removeSalle(id);
            return ResponseEntity.status(HttpStatus.OK).build();

    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRATIF','MEDECIN')")

    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<SalleDTO> getSalleById(@PathVariable Long id) {
            Salle salle = salleService.getOne(id);
            SalleDTO salleDTO = SalleDTO.toDTO(salle);
            return ResponseEntity.status(HttpStatus.OK).body(SalleDTO.toDTO(salle));

    }
    @PreAuthorize("hasAnyAuthority('ADMINISTRATIF','MEDECIN')")

    @GetMapping
    public ResponseEntity<List<SalleDTO>> getAll() {
        return ResponseEntity.ok(
                salleService.getAll().stream()
                        .map(SalleDTO::toDTO)
                        .toList()
        );
    }
    @PreAuthorize("hasAnyAuthority('ADMINISTRATIF','MEDECIN')")
    @GetMapping("/salles-disponibles")
    public ResponseEntity<List<SalleDTO>> getSallesDisponibles(@RequestParam("machine") Machine machine) {
        // Ajoutez un log pour vérifier la valeur de machine.
        System.out.println("Machine selected: " + machine.toString());

        List<Salle> sallesDisponibles = salleService.getSallesDisponibles(machine);

        return ResponseEntity.ok(
                sallesDisponibles.stream()
                        .map(SalleDTO::toDTO)
                        .toList()
        );
    }


}
