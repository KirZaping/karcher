package fr.pantheonsorbonne.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String owner;
    private String brand;
    private String model;
    private double pricePerDay;
    private LocalDateTime startDateAvailability;
    private LocalDateTime endDateAvailability;
    private String type;
    private String insurance;
    private String image;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public LocalDateTime getStartDateAvailability() {
        return startDateAvailability;
    }

    public void setStartDateAvailability(LocalDateTime startDateAvailability) {
        this.startDateAvailability = startDateAvailability;
    }

    public LocalDateTime getEndDateAvailability() {
        return endDateAvailability;
    }

    public void setEndDateAvailability(LocalDateTime endDateAvailability) {
        this.endDateAvailability = endDateAvailability;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
} 