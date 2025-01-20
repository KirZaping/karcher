package fr.pantheonsorbonne.controller;

import fr.pantheonsorbonne.service.LenderService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/Lender")
public class LenderController {
    @Inject
    LenderService lenderService;
    @GET
    @Path("/availability")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public String getAvailability(@QueryParam("CarId") String id, @QueryParam("start_date") String start_date, @QueryParam("end_date") String end_date){
        return "";//lenderService.checkAvailability(id, start_date, end_date);
    }
        
}