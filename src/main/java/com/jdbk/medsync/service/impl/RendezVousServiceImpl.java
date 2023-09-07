package com.jdbk.medsync.service.impl;

import com.jdbk.medsync.exception.AlreadyBusySalleException;
import com.jdbk.medsync.exception.NotFoundException;
import com.jdbk.medsync.model.DTO.DemandeDTO;
import com.jdbk.medsync.model.entity.RendezVous;
import com.jdbk.medsync.model.entity.Salle;
import com.jdbk.medsync.model.entity.User;
import com.jdbk.medsync.repository.RendezVousRepository;
import com.jdbk.medsync.service.notImpl.RendezVousService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class RendezVousServiceImpl implements RendezVousService {
    private final RendezVousRepository rendezVousRepository;

    public RendezVousServiceImpl(RendezVousRepository rendezVousRepository) {
        this.rendezVousRepository = rendezVousRepository;
    }

    @Override
    public Long addRendezVous(RendezVous rendezVous) {
        rendezVous.setId(null);
        List<RendezVous> rendezVousChevauchants = rendezVousRepository.findInConflit(
                rendezVous.getDateDebut(), rendezVous.getDateFin()
        );

        if (!rendezVousChevauchants.isEmpty()) {
            Salle salle1 = rendezVous.getSalle();
            boolean salleValide = rendezVousChevauchants.stream().anyMatch(salle -> salle.getId().equals(salle1.getId()));
            throw new AlreadyBusySalleException(salle1.getId(),rendezVous.getDateDebut(),rendezVous.getDateFin());
        }
        return rendezVousRepository.save(rendezVous).getId();
    }

    @Override
    public RendezVous updateRendezVous(long id, RendezVous rendezVous) {
        // todo a verif de ouf
        RendezVous existingRendezVous = getRendezVousById(id);
        if (existingRendezVous == null) {
            throw new NotFoundException(id,RendezVous.class.toString());
        }

        List<RendezVous> rendezVousChevauchants = rendezVousRepository.findOtherInConflict(
                rendezVous.getDateDebut(), rendezVous.getDateFin(),id
        );

        if (!rendezVousChevauchants.isEmpty()) {
            throw new AlreadyBusySalleException(rendezVous.getSalle().getId(),rendezVous.getDateDebut(),rendezVous.getDateFin());
        }

        existingRendezVous.setDateDebut(rendezVous.getDateDebut());
        existingRendezVous.setDateFin(rendezVous.getDateFin());

        return rendezVousRepository.save(existingRendezVous);
    }

    @Override
    public RendezVous getRendezVousById(Long idRendezVous) {
        return rendezVousRepository.findById(idRendezVous).orElseThrow(() -> new NotFoundException(idRendezVous,RendezVous.class.toString()));
    }

    @Override
    public List<RendezVous> getAll() {
        return rendezVousRepository.findAll().stream()
                .toList();
    }

    @Override
    public RendezVous removeRendezVous(Long rendezVousId) {
        RendezVous rendezVous = getRendezVousById(rendezVousId);
        rendezVousRepository.delete(rendezVous);
        return rendezVous;
    }

    @Override
    public List<RendezVous> getAllRendezVousForUser(User user) {
        return rendezVousRepository.getRendezVousByUserId(user.getId());
    }

}
