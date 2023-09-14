package com.jdbk.medsync.service.impl;

import com.jdbk.medsync.exception.AlreadyBusySalleException;
import com.jdbk.medsync.exception.NotFoundException;
import com.jdbk.medsync.model.entity.RendezVous;
import com.jdbk.medsync.model.entity.Salle;
import com.jdbk.medsync.model.entity.User;
import com.jdbk.medsync.repository.RendezVousRepository;
import com.jdbk.medsync.service.notImpl.RendezVousService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class RendezVousServiceImpl implements RendezVousService {
    private final RendezVousRepository rendezVousRepository;

    public RendezVousServiceImpl(RendezVousRepository rendezVousRepository) {
        this.rendezVousRepository = rendezVousRepository;
    }

    @Override
    public Long addRendezVous(RendezVous rendezVous) {
        rendezVous.setId(null);
        List<RendezVous> rendezVousChevauchants = rendezVousRepository.findInConflit(
                rendezVous.getDateDebut(), rendezVous.getDateFin()
        );

        if (!rendezVousChevauchants.isEmpty()) {
            Salle salle1 = rendezVous.getSalle();
            boolean salleValide = rendezVousChevauchants.stream().anyMatch(salle -> salle.getId().equals(salle1.getId()));
            throw new AlreadyBusySalleException(salle1.getId(),rendezVous.getDateDebut(),rendezVous.getDateFin());
        }
        return rendezVousRepository.save(rendezVous).getId();
    }

    @Override
    public RendezVous updateRendezVous(long id, RendezVous rendezVous) {
        // todo a verif de ouf
        RendezVous existingRendezVous = getRendezVousById(id);
        if (existingRendezVous == null) {
            throw new NotFoundException(id,RendezVous.class.toString());
        }

        List<RendezVous> rendezVousChevauchants = rendezVousRepository.findOtherInConflict(
                rendezVous.getDateDebut(), rendezVous.getDateFin(),id
        );

        if (!rendezVousChevauchants.isEmpty()) {
            throw new AlreadyBusySalleException(rendezVous.getSalle().getId(),rendezVous.getDateDebut(),rendezVous.getDateFin());
        }

        existingRendezVous.setDateDebut(rendezVous.getDateDebut());
        existingRendezVous.setDateFin(rendezVous.getDateFin());

        return rendezVousRepository.save(existingRendezVous);
    }

    @Override
    public RendezVous getRendezVousById(Long idRendezVous) {
        return rendezVousRepository.findById(idRendezVous).orElseThrow(() -> new NotFoundException(idRendezVous,RendezVous.class.toString()));
    }

    @Override
    public List<RendezVous> getAll() {
        return rendezVousRepository.findAll().stream()
                .toList();
    }

    @Override
    public RendezVous removeRendezVous(Long rendezVousId) {
        RendezVous rendezVous = getRendezVousById(rendezVousId);
        rendezVousRepository.delete(rendezVous);
        return rendezVous;
    }

    @Override
    public List<RendezVous> getAllRendezVousForUser(User user) {
        return rendezVousRepository.getRendezVousByUserId(user.getId());
    }

//    @Override
//    public List<LocalDateTime> getDatesHeuresDisponibles(Long salleId, int demandeDuree) {
//        // 1. Obtenez tous les rendez-vous pour la salle spécifiée
//        List<RendezVous> rendezVousSalle = rendezVousRepository.findBySalleId(salleId);
//
//        // 2. Créez une liste de toutes les dates possibles pour la salle
//        List<LocalDateTime> toutesLesDates = genererToutesLesDates();
//
//        // Vérifiez d'abord si la liste des rendez-vous n'est pas vide
//        if (!rendezVousSalle.isEmpty()) {
//            // 3. Parcourez les rendez-vous existants et supprimez les dates déjà réservées
//            for (RendezVous rdv : rendezVousSalle) {
//                Iterator<LocalDateTime> dateIterator = toutesLesDates.iterator();
//                while (dateIterator.hasNext()) {
//                    LocalDateTime date = dateIterator.next();
//                    if (!dateEstAvantOuEgale(date, rdv.getDateDebut()) && !dateEstApresOuEgale(date, rdv.getDateFin())) {
//                        dateIterator.remove();
//                    }
//                }
//            }
//        }
//
//        // 4. Maintenant, filtrez les dates restantes pour ne conserver que celles
//        //    qui ont une plage horaire suffisamment longue pour la demande
//        List<LocalDateTime> datesHeuresDisponibles = new ArrayList<>();
////        for (LocalDateTime date : toutesLesDates) {
////            LocalDateTime dateFinDemande = date.plusMinutes(demandeDuree);
//////            if (rendezVousSalle.isEmpty() || dateEstAvantOuEgale(dateFinDemande, rendezVousSalle.get(0).getDateFin())) {
////            if (rendezVousSalle.isEmpty() || dateEstAvantOuEgale(dateFinDemande, rendezVousSalle.get(0).getDateFin())){
////
////                datesHeuresDisponibles.add(date);
////            }
////        }
//        for (LocalDateTime date : toutesLesDates) {
//            LocalDateTime dateFinDemande = date.plusMinutes(demandeDuree);
//            boolean dateDisponible = true;
//
//            // Vérifier si la date est en conflit avec l'un des rendez-vous existants
//            for (RendezVous rdv : rendezVousSalle) {
//                if (!dateEstAvantOuEgale(dateFinDemande, rdv.getDateDebut()) && !dateEstApresOuEgale(date, rdv.getDateFin())) {
//                    dateDisponible = false;
//                    break; // Sortir de la boucle dès qu'un conflit est trouvé
//                }
//            }
//
//            // Si la date n'est pas en conflit avec tous les rendez-vous, elle est disponible
//            if (dateDisponible) {
//                datesHeuresDisponibles.add(date);
//            }
//        }
//
//// Supprimer les dates utilisées par le premier rendez-vous
//        if (!rendezVousSalle.isEmpty()) {
//            RendezVous premierRdv = rendezVousSalle.get(0);
//            Iterator<LocalDateTime> dateIterator = datesHeuresDisponibles.iterator();
//            while (dateIterator.hasNext()) {
//                LocalDateTime date = dateIterator.next();
//                if (!dateEstAvantOuEgale(date, premierRdv.getDateFin())) {
//                    dateIterator.remove();
//                }
//            }
//        }
//        return datesHeuresDisponibles;
//    }

    @Override
    public List<LocalDateTime> getDatesHeuresDisponibles(Long salleId, int demandeDuree) {
        // 1. Obtenez tous les rendez-vous pour la salle spécifiée
        List<RendezVous> rendezVousSalle = rendezVousRepository.findBySalleId(salleId);

        // 2. Créez une liste de toutes les dates possibles pour la salle
        List<LocalDateTime> toutesLesDates = genererToutesLesDates();

        // 3. Parcourez les rendez-vous existants et marquez les plages horaires comme indisponibles
        for (RendezVous rdv : rendezVousSalle) {
            LocalDateTime debutRdv = rdv.getDateDebut();
            LocalDateTime finRdv = rdv.getDateFin();

            // Parcourez toutes les dates possibles
            Iterator<LocalDateTime> dateIterator = toutesLesDates.iterator();
            while (dateIterator.hasNext()) {
                LocalDateTime date = dateIterator.next();
                if (dateEstApresOuEgale(date, finRdv) && dateEstAvantOuEgale(date, debutRdv)) {
//                    System.out.println("date=>"+date.toString() );
//                    System.out.println("date fin rdv =>"+finRdv.toString() );
//                    System.out.println("true ou false =>"+dateEstApresOuEgale(date, finRdv));
                    dateIterator.remove(); // Marquez la date comme indisponible
                }
            }
        }

        // 4. Maintenant, filtrez les dates restantes pour ne conserver que celles
        //    qui ont une plage horaire suffisamment longue pour la demande
        List<LocalDateTime> datesHeuresDisponibles = new ArrayList<>();
        for (LocalDateTime date : toutesLesDates) {
            LocalDateTime dateFinDemande = date.plusMinutes(demandeDuree);
            if (rendezVousSalle.isEmpty() ||
                    dateEstAvantOuEgale(date, rendezVousSalle.get(0).getDateDebut())||
                    dateEstApresOuEgale(dateFinDemande, rendezVousSalle.get(0).getDateFin())) {
                datesHeuresDisponibles.add(date);
            }
        }

        return datesHeuresDisponibles;
    }

    // Méthode pour générer toutes les dates possibles pour une salle (à personnaliser)
    private List<LocalDateTime> genererToutesLesDates() {
        List<LocalDateTime> dates = new ArrayList<>();
        LocalDateTime dateDebut = LocalDateTime.now(); // Date actuelle
        int minutesArrondies = (int) (Math.round(dateDebut.getMinute() / 10.0) * 10);

        // Mettre à jour la date avec les minutes arrondies
//        dateDebut = dateDebut.withMinute(minutesArrondies);
        dateDebut =  dateDebut.withHour(dateDebut.getHour()+2);
        dateDebut =  dateDebut.withMinute(0);
        LocalDateTime dateFin = dateDebut.plusDays(10); // Exemple : Générer des dates pour 1 an à partir de maintenant
        int frequenceMinutes = 60; // Fréquence en minutes

        while (!dateDebut.isAfter(dateFin)) {
            dates.add(dateDebut);
            dateDebut = dateDebut.plusMinutes(frequenceMinutes);
        }

        return dates;
    }

    // Méthode pour comparer si une date est avant ou égale à une autre date
    private boolean dateEstAvantOuEgale(LocalDateTime date1, LocalDateTime date2) {
        return !date1.isAfter(date2)&& !date1.isEqual(date2);
    }

    // Méthode pour comparer si une date est après ou égale à une autre date
    private boolean dateEstApresOuEgale(LocalDateTime date1, LocalDateTime date2) {
        return !date1.isBefore(date2) && !date1.isEqual(date2);
    }

}
