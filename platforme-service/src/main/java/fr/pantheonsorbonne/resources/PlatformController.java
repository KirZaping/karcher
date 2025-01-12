package fr.pantheonsorbonne.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/platforme")
public class PlatformController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {
        return "{\"message\": \"Hello\"}";
    }
} 