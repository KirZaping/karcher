package fr.pantheonsorbonne.controller;

import fr.pantheonsorbonne.service.CarService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;

@Path("/cars")
public class CarController {

    @Inject
    CarService carService;

    @Inject
    ObjectMapper objectMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCars() {
        try {
            return objectMapper.writeValueAsString(carService.getAllCars());
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\": \"Unable to retrieve cars\"}";
        }
    }

    @GET
    @Path("/available")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAvailableCars(@jakarta.ws.rs.QueryParam("location") String location,
                                   @jakarta.ws.rs.QueryParam("startDate") LocalDate startDate,
                                   @jakarta.ws.rs.QueryParam("endDate") LocalDate endDate) {
        try {
            return objectMapper.writeValueAsString(carService.getAvailableCars(location, startDate, endDate));
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\": \"Unable to retrieve available cars\"}";
        }
    }

    // @GET
    // @Path("/add-car")
    // @Produces(MediaType.TEXT_HTML)
    // public String showAddCarPage() {
    //     // Retourner le contenu HTML de la page d'ajout de voiture
    //     return "<html><body><h1>Ajouter une Voiture</h1></body></html>"; // Remplacez par le contenu de add-car.html
    // }

    // @POST
    // @Produces(MediaType.APPLICATION_JSON)
    // public void addCar(Car car) {
    //     carService.addCar(car);
    // }

    // @POST
    // @Path("/add-default-car")
    // @Produces(MediaType.APPLICATION_JSON)
    // public Response addDefaultCar() {
    //     try {
    //         carService.addDefaultCar();
    //         return Response.ok().entity("Voiture par défaut ajoutée avec succès.").build();
    //     } catch (Exception e) {
    //         return Response.serverError().entity("Erreur lors de l'ajout de la voiture par défaut : " + e.getMessage()).build();
    //     }
    // }
} 