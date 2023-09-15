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
import org.springframework.cglib.core.Local;

import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class RendezVousForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

//    @Future
    @NotNull
    private LocalDateTime dateDebut;
//    @Future
    @NotNull
    private LocalDateTime dateFin;

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
