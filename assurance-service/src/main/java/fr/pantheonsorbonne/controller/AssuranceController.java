package fr.pantheonsorbonne.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/assurance")
public class AssuranceController {

    @GET
    public String getAssuranceInfo() {
        return "{\"info\": \"Information sur l'assurance\"}";
    }
} 