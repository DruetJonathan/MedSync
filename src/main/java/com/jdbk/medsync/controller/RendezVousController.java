package com.jdbk.medsync.controller;

import com.jdbk.medsync.model.DTO.DemandeDTO;
import com.jdbk.medsync.model.DTO.RendezVousDTO;
import com.jdbk.medsync.model.entity.RendezVous;
import com.jdbk.medsync.model.entity.User;
import com.jdbk.medsync.model.form.RendezVousForm;
import com.jdbk.medsync.service.notImpl.DemandeService;
import com.jdbk.medsync.service.notImpl.RendezVousService;
import com.jdbk.medsync.service.notImpl.SalleService;
import com.jdbk.medsync.service.notImpl.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/rendezvous")
public class RendezVousController {
    private final RendezVousService rendezVousService;
    private final SalleService salleService;
    private final DemandeService demandeService;
    private final UserService userService;

    public RendezVousController(RendezVousService rendezVousService, SalleService salleService, DemandeService demandeService, UserService userService) {
        this.rendezVousService = rendezVousService;
        this.salleService = salleService;
        this.demandeService = demandeService;
        this.userService = userService;
    }
    @PreAuthorize("hasRole('ADMINISTRATIF')")

    @PostMapping("/add")
    public ResponseEntity<Long> addRendezVous(@RequestBody @Valid RendezVousForm form) {
        RendezVous entity = form.toEntity();
        entity.setCreePar(userService.getOne(form.getIdUser()));
        entity.setDemande(demandeService.getOne(form.getDemande()));
        entity.setSalle(salleService.getOne(form.getSalle()));
        Long idRendezVous = rendezVousService.addRendezVous(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(idRendezVous);

    }
    @PreAuthorize("hasRole('ADMINISTRATIF')")

    @PutMapping("/{id:[0-9]+}")
    public ResponseEntity<RendezVousDTO> updateRendezVous(@PathVariable Long id, @RequestBody @Valid RendezVousForm form) {
        RendezVous entity = form.toEntity();
        RendezVous rendezVous = rendezVousService.updateRendezVous(id, entity);
        return ResponseEntity.status(HttpStatus.OK).body(RendezVousDTO.toDTO(rendezVous));
    }
    @PreAuthorize("hasRole('ADMINISTRATIF')")
    @DeleteMapping("/{id:[0-9]+}")
    public ResponseEntity<String> removeRendezVous(@PathVariable Long id) {
        RendezVous rendezVous = rendezVousService.removeRendezVous(id);
        return ResponseEntity.status(HttpStatus.OK).body("Rendez-vous deleted");
    }
    @PreAuthorize("hasAnyRole('ADMINISTRATIF','MEDECIN')")
    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<RendezVousDTO> getRendezVousById(@PathVariable Long id) {
        RendezVous rendezVous = rendezVousService.getRendezVousById(id);
        RendezVousDTO rendezVousDTO = RendezVousDTO.toDTO(rendezVous);
        return ResponseEntity.status(HttpStatus.OK).body(rendezVousDTO);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATIF','MEDECIN')")
    @GetMapping
    public ResponseEntity<List<RendezVousDTO>> getAll() {
        return ResponseEntity.ok(
                rendezVousService.getAll().stream()
                        .map(RendezVousDTO::toDTO)
                        .toList()
        );
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATIF','MEDECIN')")
    @GetMapping("/specificRendezVous/user/{id:[0-9]+}")
    public ResponseEntity<List<RendezVousDTO>> getAllRendezVousForUser(@PathVariable("id") Long idUser) {
        User user = userService.getOne(idUser);
        return ResponseEntity.ok(
                rendezVousService.getAllRendezVousForUser(user).stream()
                        .map(RendezVousDTO::toDTO)
                        .toList()
        );
    }
}
