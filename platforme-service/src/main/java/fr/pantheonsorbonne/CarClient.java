package fr.pantheonsorbonne;

import fr.pantheonsorbonne.model.Car;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.GenericType;
import java.util.List;

public class CarClient {

    private static final String BASE_URL = "http://localhost:8080/cars"; // URL de l'API de platforme-service
    private final Client client;

    public CarClient() {
        this.client = ClientBuilder.newClient();
    }

    public List<Car> getAllCars() {
        WebTarget target = client.target(BASE_URL);
        Response response = target.request(MediaType.APPLICATION_JSON).get();

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            GenericType<List<Car>> genericType = new GenericType<List<Car>>() {};
            return response.readEntity(genericType);
        } else {
            throw new RuntimeException("Échec de la récupération des voitures : " + response.getStatus());
        }
    }
} 