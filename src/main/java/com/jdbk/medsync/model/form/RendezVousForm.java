package com.jdbk.medsync.model.form;

import com.jdbk.medsync.model.entity.Demande;
import com.jdbk.medsync.model.entity.RendezVous;
import com.jdbk.medsync.model.entity.Salle;
import com.jdbk.medsync.model.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.security.PublicKey;
import java.util.Date;

@Data
@Builder
public class RendezVousForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Future
    @NotNull
    private Date dateDebut;
    @Future
    @NotNull
    private Date dateFin;

    private Long idUser;
    private Long salle;
    private Long demande;

    public RendezVous toEntity(){
        RendezVous rendezVous = new RendezVous();
        rendezVous.setDateDebut(dateDebut);
        rendezVous.setDateFin(dateFin);
        return rendezVous;
    }
}
