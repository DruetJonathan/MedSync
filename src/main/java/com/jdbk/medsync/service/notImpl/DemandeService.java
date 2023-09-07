package com.jdbk.medsync.service.notImpl;

import com.jdbk.medsync.model.entity.Demande;

import java.util.List;

public interface DemandeService {

    public Long addDemande(Demande demande);

    public Demande getOne(Long id);

    public Demande updateDemande(long id, Demande demande);

    public Demande removeDemande(Long demandeId);

    public List<Demande> getAll();


}
