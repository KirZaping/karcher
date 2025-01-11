package fr.pantheonsorbonne.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/platform")
public class PlatformController {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getAvailableCars() {
        return "<h1>Liste des voitures disponibles</h1>";
    }
} 