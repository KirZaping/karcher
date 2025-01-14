package fr.pantheonsorbonne.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.QueryParam;
import fr.pantheonsorbonne.model.Assurance;

@Path("/assurance")
public class AssuranceController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAssuranceInfo(@QueryParam("type") String type, @QueryParam("age") double age, @QueryParam("duree_permis") double duree_permis) {
        Assurance assurance = new Assurance();
        assurance.setPrice(age, duree_permis, type);
        return "{\"info\": \"Information sur l'assurance - type: " + type + " - age: " + age + " - duree_permis: " + duree_permis + " - prix: " + assurance.getPrice() + "\"}";
    }
}