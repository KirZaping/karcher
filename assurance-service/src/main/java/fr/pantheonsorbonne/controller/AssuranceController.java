package fr.pantheonsorbonne.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/assurance")
public class AssuranceController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAssurance(@QueryParam("type") String type, @QueryParam("age") double age, @QueryParam("duree_permis") double duree_permis) {
        // Redirige vers la route Camel
        return "{\"info\": \"Utilisez la route Camel pour obtenir les informations d'assurance\"}";
    }
}