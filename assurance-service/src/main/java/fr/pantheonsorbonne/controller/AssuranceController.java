package fr.pantheonsorbonne.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("assurance")
public class AssuranceController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAssuranceInfo() {
        return "{\"info\": \"Information sur l'assurance\"}";
    }
} 