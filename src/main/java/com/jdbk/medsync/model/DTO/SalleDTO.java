package com.jdbk.medsync.model.DTO;

import com.jdbk.medsync.model.Enum.Machine;
import com.jdbk.medsync.model.entity.RendezVous;
import com.jdbk.medsync.model.entity.Salle;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class SalleDTO {

    private Long id;
    private int etage;
    private String numeroSalle;
    private Machine machine;
    private Set<RendezVous> rendezVous;

    public static SalleDTO  toDTO(Salle salle) {
        if (salle == null)
            return null;
        return SalleDTO.builder()
                .id(salle.getId())
                .etage(salle.getEtage())
                .numeroSalle(salle.getNumeroSalle())
                .machine(salle.getMachine())
                .rendezVous(salle.getRendezVous())
                .build();
    }
}
