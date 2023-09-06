package com.jdbk.medsync.repository;

import com.jdbk.medsync.model.entity.Demande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeRepository extends JpaRepository<Demande,Long>{
}
