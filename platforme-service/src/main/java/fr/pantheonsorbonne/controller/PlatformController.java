package fr.pantheonsorbonne.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
//import jakarta.ws.rs.core.Response;
//import jakarta.inject.Inject;
//import fr.pantheonsorbonne.cli.UserInterfaceCLI;

@Path("platforme")
public class PlatformController {

    @GET
    @Path("/assurance")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAssuranceInfo() {
        return "{\"info\": \"Information sur l'assurance\"}";
    }

    // @GET
    // @Path("/user")
    // @Produces(MediaType.APPLICATION_JSON)
    // public String  interactWithUser() {
    //     // This could trigger the CLI interaction
    //     return "CLI triggered";
    //     //userInterfaceCLI.start(); // Call the start method to begin CLI interaction
    //     //return Response.ok("{\"info\": \"Interacting with the user through CLI\"}").build();
    // }
} 