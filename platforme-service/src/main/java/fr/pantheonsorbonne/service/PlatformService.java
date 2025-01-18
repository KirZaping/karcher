package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.model.Platforme;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PlatformService {

    private final Platforme platforme; // Déclaration de l'objet Platforme

    @Inject
    public PlatformService(Platforme platforme) { // Constructeur pour l'injection de dépendance
        this.platforme = platforme;
    }

    public String getPriceAssurance(double age, double duree_permis, String type) {
        // Appel au service d'assurance via le gateway
        String response = platforme.getNom();
        return response;
    }
    // Logique de service pour gérer les assurances
} 