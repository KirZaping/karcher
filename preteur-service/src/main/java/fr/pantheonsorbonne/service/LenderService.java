package fr.pantheonsorbonne.service;

import java.util.Random;

import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LenderService {

    private final Random random;



    public LenderService() {
        this.random = new Random();
    }

    public String lenderConfirmation(double carId) {
        JsonObject response = new JsonObject();
        response.put("status", random.nextInt(2) == 0 ? "reject" : "accept");
        response.put("carid", carId);
        return response.toString();
    }
}