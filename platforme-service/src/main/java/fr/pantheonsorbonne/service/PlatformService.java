package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.model.Platforme;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import fr.pantheonsorbonne.model.Car;
import java.time.LocalDate;
import java.util.List;
import fr.pantheonsorbonne.repository.CarRepository;

@ApplicationScoped
public class PlatformService {

    private final Platforme platforme; // Déclaration de l'objet Platforme

    @Inject
    public PlatformService(Platforme platforme) { // Constructeur pour l'injection de dépendance
        this.platforme = platforme;
    }

    @Inject
    CarRepository carRepository;

    public String getPriceAssurance(double age, double duree_permis, String type) {
        // Appel au service d'assurance via le gateway
        String response = platforme.getNom();
        return response;
    }

    public List<Car> getAvailableCars(String location, LocalDate startDate, LocalDate endDate) {
        // Logic to fetch available cars based on location and dates
        return carRepository.findAvailableCars(location, startDate, endDate);
    }

    // Logique de service pour gérer les assurances
} 