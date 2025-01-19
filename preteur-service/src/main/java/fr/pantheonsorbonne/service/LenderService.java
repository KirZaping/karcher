package fr.pantheonsorbonne.service;

import java.util.Random;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LenderService {

    private final Random random;



    public LenderService() {
        this.random = new Random();
    }

    @Transactional
    public String checkAvailability(String carId, String startDate, String endDate) {
        // Retourne une série de tirages pour la disponibilité
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < 5; i++) { // Simule 5 tirages
            if (i > 0) {
                result.append(", ");
            }
            result.append(random.nextInt(2) == 0 
                ? String.format("{\"carId\": \"%s\", \"startDate\": \"%s\", \"endDate\": \"%s\", \"status\": \"available\"}", carId, startDate, endDate) 
                : String.format("{\"carId\": \"%s\", \"startDate\": \"%s\", \"endDate\": \"%s\", \"status\": \"not available\"}", carId, startDate, endDate));
        }
        result.append("]");
        return result.toString();
    }
}