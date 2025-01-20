package fr.pantheonsorbonne.service;

import java.util.Random;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LenderService {

    private final Random random;



    public LenderService() {
        this.random = new Random();
    }

    public String lenderConfirmation() {
        return random.nextInt(5) == 0 ? "{\"status\": \"unavailable\"}" : "{\"status\": \"available\"}";
    }
}