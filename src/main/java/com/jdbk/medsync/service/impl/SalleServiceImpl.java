package com.jdbk.medsync.service.impl;

import com.jdbk.medsync.exception.AlreadyExistException;
import com.jdbk.medsync.exception.NotFoundException;
import com.jdbk.medsync.model.entity.Salle;
import com.jdbk.medsync.repository.SalleRepository;
import com.jdbk.medsync.service.notImpl.SalleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalleServiceImpl implements SalleService {
    private final SalleRepository salleRepository;

    public SalleServiceImpl(SalleRepository salleRepository) {
        this.salleRepository = salleRepository;
    }

    @Override
    public Long addSalle(Salle salle) {
        salle.setId(null);
        if (salleRepository.existsByEtageAndNumeroSalle(salle.getEtage(),salle.getNumeroSalle())){
            throw new AlreadyExistException(salle.getId(),SalleServiceImpl.class.toString());
        }
        salle = salleRepository.save(salle);
        return salle.getId();
    }

    @Override
    public Salle getOne(Long id) {
        return salleRepository.findById(id).orElseThrow(() -> new NotFoundException(id,SalleServiceImpl.class.toString()));
    }

    @Override
    public Salle updateSalle(long id, Salle salle) {
        if (salle == null && !salleRepository.existsById(id)){
            throw new NotFoundException(id,SalleServiceImpl.class.toString());
        }
        salle.setId(id);
        return salleRepository.save(salle);
    }

    @Override
    public Salle removeSalle(Long saleId) {
        Salle salle = getOne(saleId);
        salleRepository.delete(salle);
        return salle;
    }

    @Override
    public List<Salle> getAll() {
        return salleRepository.findAll().stream()
                .toList();
    }
}
