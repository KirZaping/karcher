package fr.pantheonsorbonne.service;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.Random;

@ApplicationScoped
public class LenderService {

    private final Random random;

    public LenderService() {
        this.random = new Random();
    }

    public String checkAvailability(String carId, String startDate, String endDate) {
        // Simulate 50% chance of availability
        return random.nextInt(2) == 0 ? "available" : "not available";
    }
}