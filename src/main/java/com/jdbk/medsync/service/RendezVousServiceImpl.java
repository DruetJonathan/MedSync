package com.jdbk.medsync.service;

import com.jdbk.medsync.exception.AlreadyBusySalleException;
import com.jdbk.medsync.exception.NotFoundException;
import com.jdbk.medsync.model.entity.Produit;
import com.jdbk.medsync.model.entity.RendezVous;
import com.jdbk.medsync.model.entity.Salle;
import com.jdbk.medsync.repository.RendezVousRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RendezVousServiceImpl implements RendezVousService {
    private final RendezVousRepository rendezVousRepository;

    public RendezVousServiceImpl(RendezVousRepository rendezVousRepository) {
        this.rendezVousRepository = rendezVousRepository;
    }

    @Override
    public Long addRendezVous(RendezVous rendezVous) {
        rendezVous.setId(null);
        List<RendezVous> rendezVousChevauchants = rendezVousRepository.findByDateDebutBetweenOrDateFinBetween(
                rendezVous.getDateDebut(), rendezVous.getDateFin(), rendezVous.getDateDebut(), rendezVous.getDateFin()
        );

        if (!rendezVousChevauchants.isEmpty()) {
            Salle salle1 = rendezVous.getSalle();
            boolean salleValide = rendezVousChevauchants.stream().anyMatch(salle -> salle.getId().equals(salle1.getId()));
            throw new AlreadyBusySalleException("Des rendez-vous se chevauchent avec les dates spécifiées à la salle: " + salle1.getEtage() + " " + salle1.getNumeroSalle());
        }
        return rendezVousRepository.save(rendezVous).getId();
    }

    @Override
    public RendezVous updateRendezVous(long id, RendezVous rendezVous) {
        // todo a verif de ouf
        RendezVous existingRendezVous = getRendezVousById(id);
        if (existingRendezVous == null) {
            throw new NotFoundException("Rendez-vous non trouvé avec l'ID: " + id);
        }

        List<RendezVous> rendezVousChevauchants = rendezVousRepository.findByDateDebutBetweenOrDateFinBetweenAndIdNot(
                rendezVous.getDateDebut(), rendezVous.getDateFin(),
                rendezVous.getDateDebut(), rendezVous.getDateFin(), id
        );

        if (!rendezVousChevauchants.isEmpty()) {
            throw new AlreadyBusySalleException("Des rendez-vous se chevauchent avec les dates spécifiées.");
        }

        existingRendezVous.setDateDebut(rendezVous.getDateDebut());
        existingRendezVous.setDateFin(rendezVous.getDateFin());

        return rendezVousRepository.save(existingRendezVous);
    }

    @Override
    public RendezVous getRendezVousById(Long idRendezVous) {
        return rendezVousRepository.findById(idRendezVous).orElseThrow(() -> new NotFoundException("Rendez-vous not found"));
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
}
