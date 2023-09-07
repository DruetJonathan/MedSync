package com.jdbk.medsync.repository;

import com.jdbk.medsync.model.entity.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalleRepository extends JpaRepository<Salle,Long> {
    boolean existsByEtageAndNumeroSalle(int numeroEtage, String numeroSalle);

}
