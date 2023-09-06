package com.jdbk.medsync.repository;

import com.jdbk.medsync.model.entity.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous,Long> {
    List<RendezVous> findByDateDebutBetweenOrDateFinBetween(Date debut1, Date fin1, Date debut2, Date fin2);
    List<RendezVous> findByDateDebutBetweenOrDateFinBetweenAndIdNot(
            Date debut1, Date fin1, Date debut2, Date fin2, Long id
    );
}
