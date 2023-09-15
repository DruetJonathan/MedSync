package com.jdbk.medsync.model.DTO;

import com.jdbk.medsync.model.entity.Demande;
import com.jdbk.medsync.model.entity.RendezVous;
import com.jdbk.medsync.model.entity.Salle;
import com.jdbk.medsync.model.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class RendezVousDTO {

    private Long id;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private Demande demande;
    private String creePar;
    private SalleDTOSansRdv salle;

    public static RendezVousDTO toDTO(RendezVous rendezVous){
        if( rendezVous == null )
            return null;
        return RendezVousDTO.builder()
                .id(rendezVous.getId())
                .dateDebut(rendezVous.getDateDebut())
                .dateFin(rendezVous.getDateFin())
                .demande(rendezVous.getDemande())
                .creePar(rendezVous.getCreePar().getFirstname()+" "+rendezVous.getCreePar().getLastname())
                .salle(SalleDTOSansRdv.sansRDV(rendezVous.getSalle()))
                .build();

    }

}
