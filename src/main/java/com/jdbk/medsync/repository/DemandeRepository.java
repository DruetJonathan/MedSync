package com.jdbk.medsync.repository;

import com.jdbk.medsync.model.entity.Demande;
import com.jdbk.medsync.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandeRepository extends JpaRepository<Demande,Long>{
    List<Demande> getDemandesByDemandeur(User demandeur);
}
