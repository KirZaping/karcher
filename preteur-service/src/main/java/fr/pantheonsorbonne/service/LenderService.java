package fr.pantheonsorbonne.service;

import java.util.Random;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LenderService {

    private final Random random;



    public LenderService() {
        this.random = new Random();
    }

    public String lenderConfirmation(double carId) {
        return random.nextInt(2) == 0 ? "{\"status\": \"reject\", \"carid\": \"" + carId + "\"}" : "{\"status\": \"accept\", \"carid\": \"" + carId + "\"}";
    }
}