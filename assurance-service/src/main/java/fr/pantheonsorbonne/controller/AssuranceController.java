package fr.pantheonsorbonne.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.QueryParam;
import fr.pantheonsorbonne.model.Assurance;
import fr.pantheonsorbonne.service.AssuranceService;
import jakarta.inject.Inject;

@Path("/assurance")
public class AssuranceController {
    @Inject
    AssuranceService assuranceService;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAssurance(@QueryParam("type") String type, @QueryParam("age") double age, @QueryParam("duree_permis") double duree_permis){
        return assuranceService.getAssuranceInfo(age,duree_permis,type);
    }
        
}