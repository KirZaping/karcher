package fr.pantheonsorbonne.controller;

import fr.pantheonsorbonne.service.CarService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/cars")
public class CarController {

    @Inject
    CarService carService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCars() {
        return carService.getAllCars(); // Appelle le service pour récupérer toutes les voitures
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