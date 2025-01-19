package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.model.Assurance;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AssuranceService {

    @Inject
    private final Assurance assurance; // Déclaration de l'objet Assurance

    public AssuranceService(Assurance assurance) { // Constructeur pour l'injection de dépendance
        this.assurance = assurance;
    }

    public void setAssuranceInfo(double age, double duree_permis, String type) {
        assurance.setAge(age);
        assurance.setDureePermis(duree_permis);
        assurance.setType(type);
        assurance.setPrice();
    }

    public String getAssuranceInfo() {
        return String.format("{\"Récapitulatif commande\": \"type: %s - age: %.2f - duree_permis: %.2f\", \"prix\": %.2f, \"Assureure\": \" %s\"}", 
                             assurance.getType(), assurance.getAge(), assurance.getDureePermis(), assurance.getPrice(), assurance.getAssureur());
    }

} 