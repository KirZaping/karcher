package fr.pantheonsorbonne.service;
import fr.pantheonsorbonne.model.Assurance;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class AssuranceService {
    public String getAssuranceInfo(double age, double duree_permis, String type) {
        Assurance assurance = new Assurance();
        assurance.setPrice(age, duree_permis, type);
        return "{\"info\": \"Information sur l'assurance - type: " + type + " - age: " + age + " - duree_permis: " + duree_permis + " - prix: " + assurance.getPrice() + "\"}";
    }
    // Logique de service pour gérer les assurances
} 