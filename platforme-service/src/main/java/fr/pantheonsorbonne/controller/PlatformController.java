package fr.pantheonsorbonne.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/platform")
public class PlatformController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAvailableCars() {
        return "{\"cars\": [\"Car1\", \"Car2\"]}";
    }
} 