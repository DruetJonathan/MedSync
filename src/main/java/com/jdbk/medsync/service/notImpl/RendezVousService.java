package com.jdbk.medsync.service.notImpl;

import com.jdbk.medsync.model.entity.RendezVous;
import com.jdbk.medsync.model.entity.User;
import com.jdbk.medsync.repository.RendezVousRepository;

import java.util.Collection;
import java.util.List;

public interface RendezVousService {
    public Long addRendezVous(RendezVous  rendezVous);
    public RendezVous updateRendezVous(long id, RendezVous rendezVous);
    public RendezVous getRendezVousById(Long idRendezVous);
    public List<RendezVous> getAll();

    public RendezVous removeRendezVous(Long rendezVousId);

    List<RendezVous> getAllRendezVousForUser(User user);
}
