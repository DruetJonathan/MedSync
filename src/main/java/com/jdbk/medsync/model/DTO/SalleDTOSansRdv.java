package com.jdbk.medsync.model.DTO;

import com.jdbk.medsync.model.entity.RendezVous;
import com.jdbk.medsync.model.entity.Salle;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
@Data
@Builder
public class SalleDTOSansRdv {

    private Long id;
    private int etage;
    private String numeroSalle;
    private String machine;
    public static SalleDTOSansRdv sansRDV(Salle salle) {
        if (salle == null)
            return null;
        return SalleDTOSansRdv.builder()
                .id(salle.getId())
                .etage(salle.getEtage())
                .numeroSalle(salle.getNumeroSalle())
                .machine(salle.getMachine().getValue())
                .build();
    }
}
