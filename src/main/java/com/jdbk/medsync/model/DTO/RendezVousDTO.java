package com.jdbk.medsync.model.DTO;

import com.jdbk.medsync.model.entity.Demande;
import com.jdbk.medsync.model.entity.RendezVous;
import com.jdbk.medsync.model.entity.Salle;
import com.jdbk.medsync.model.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RendezVousDTO {

    private Long id;
    private Date dateDebut;
    private Date dateFin;
    private Demande demande;
    private User user;
    private Salle salle;

    public static RendezVousDTO toDTO(RendezVous rendezVous){
        if( rendezVous == null )
            return null;
        return RendezVousDTO.builder()
                .id(rendezVous.getId())
                .dateDebut(rendezVous.getDateDebut())
                .dateFin(rendezVous.getDateFin())
                .demande(rendezVous.getDemande())
                .user(rendezVous.getUser())
                .salle(rendezVous.getSalle())
                .build();

    }

}
