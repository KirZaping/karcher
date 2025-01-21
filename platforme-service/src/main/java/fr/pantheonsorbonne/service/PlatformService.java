package fr.pantheonsorbonne.service;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlatformService {

    private double price_assurance;

    public void setPriceAssurance(double price_assurance) {
        this.price_assurance = price_assurance;
    }

    public double getPriceAssurance() {
        return price_assurance;
    }
} 
