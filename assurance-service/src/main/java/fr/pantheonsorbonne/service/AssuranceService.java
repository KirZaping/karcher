package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.model.Assurance;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AssuranceService {

    private final Assurance assurance; // Déclaration de l'objet Assurance

    @Inject
    public AssuranceService(Assurance assurance) { // Constructeur pour l'injection de dépendance
        this.assurance = assurance;
    }

    public String getAssuranceInfo(double age, double duree_permis, String type) {
        assurance.setPrice(age, duree_permis, type);
        return String.format("{\"info\": \"Information sur l'assurance - type: %s - age: %.2f - duree_permis: %.2f - prix: %.2f\"}", 
                             type, age, duree_permis, assurance.getPrice());
    }
    // Logique de service pour gérer les assurances
} 