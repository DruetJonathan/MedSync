package com.jdbk.medsync.repository;

import com.jdbk.medsync.model.entity.Salle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalleRepository extends JpaRepository<Salle,Long> {
    boolean existsByNumeroEtageAndNumeroSalle(int numeroEtage, String numeroSalle);

}
