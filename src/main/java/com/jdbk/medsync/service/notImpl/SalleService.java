package com.jdbk.medsync.service.notImpl;

import com.jdbk.medsync.model.entity.Demande;
import com.jdbk.medsync.model.entity.Salle;

import java.util.List;

public interface SalleService {

    public Long addSalle(Salle salle);

    public Salle getOne(Long id);

    public Salle updateSalle(long id, Salle salle);

    public Salle removeSalle(Long saleId);

    public List<Salle> getAll();
}
