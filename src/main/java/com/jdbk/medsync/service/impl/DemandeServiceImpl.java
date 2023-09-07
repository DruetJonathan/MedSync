package com.jdbk.medsync.service.impl;

import com.jdbk.medsync.exception.NotFoundException;
import com.jdbk.medsync.model.entity.Demande;
import com.jdbk.medsync.model.entity.User;
import com.jdbk.medsync.repository.DemandeRepository;
import com.jdbk.medsync.service.notImpl.DemandeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandeServiceImpl implements DemandeService {

    private final DemandeRepository demandeRepository;

    public DemandeServiceImpl(DemandeRepository demandeRepository) {
        this.demandeRepository = demandeRepository;
    }

    @Override
    public Long addDemande(Demande demande) {
        demande.setId(null);
        demande = demandeRepository.save(demande);
        return demande.getId();

    }

    @Override
    public Demande getOne(Long id) {
        return demandeRepository.findById(id).orElseThrow(()->new NotFoundException(id,DemandeServiceImpl.class.toString()));
    }

    @Override
    public Demande updateDemande(long id, Demande demande) {
        if (demande == null && !demandeRepository.existsById(id)){
           throw new NotFoundException(id,DemandeServiceImpl.class.toString());
        }
        demande.setId(id);
        return demandeRepository.save(demande);
    }

    @Override
    public Demande removeDemande(Long demandeId) {

        Demande demande = getOne(demandeId);
        demandeRepository.delete(demande);
        return demande;
    }

    @Override
    public List<Demande> getAll() {
        return demandeRepository.findAll().stream()
                .toList();
    }

    @Override
    public List<Demande> getAllDemandeForDemandeur(User demandeur) {
        return demandeRepository.getDemandesByDemandeur(demandeur);
    }

}
