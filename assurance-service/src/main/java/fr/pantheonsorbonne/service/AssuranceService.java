package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.model.Assurance;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AssuranceService {

    @Inject
    private final Assurance assurance;

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
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.put("Récapitulatif commande", String.format("type: %s - age: %.2f - duree_permis: %.2f", 
                             assurance.getType(), assurance.getAge(), assurance.getDureePermis()));
        jsonResponse.put("prix", assurance.getPrice());
        jsonResponse.put("Assureure", assurance.getAssureur());
        return jsonResponse.toString();
    }

} 