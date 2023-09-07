package com.jdbk.medsync.repository;

import com.jdbk.medsync.model.entity.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous,Long> {

    @Query("""
        select r
        from RendezVous r
        where (
                r.dateDebut between ?1 and ?2 or
                r.dateFin between ?1 and ?2
            )
        """)
    List<RendezVous> findInConflit(LocalDateTime dateDebut, LocalDateTime dateFin);

    @Query("""
        select r
        from RendezVous r
        where (
                r.dateDebut between ?1 and ?2 or
                r.dateFin between ?1 and ?2
            ) and r.id != ?3
        """)
    List<RendezVous> findOtherInConflict(
            LocalDateTime dateDebut,
            LocalDateTime dateFin,
            Long id
    );
}
