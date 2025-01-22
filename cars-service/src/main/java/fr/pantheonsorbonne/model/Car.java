package fr.pantheonsorbonne.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String owner;
    private String brand;
    private String model;
    private int pricePerDay;
    private LocalDate startDateAvailability;
    private LocalDate endDateAvailability;
    private String type; 
    private String location;

    public Car() {
    }

    public Car(Long id, String owner, String brand, String model, int pricePerDay, LocalDate startDateAvailability, LocalDate endDateAvailability, String type, String location) { // Changed from double to int
        this.id = id;
        this.owner = owner;
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.startDateAvailability = startDateAvailability;
        this.endDateAvailability = endDateAvailability;
        this.type = type;
        this.location = location;
    }

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

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public LocalDate getStartDateAvailability() {
        return startDateAvailability;
    }

    public void setStartDateAvailability(LocalDate startDateAvailability) {
        this.startDateAvailability = startDateAvailability;
    }

    public LocalDate getEndDateAvailability() {
        return endDateAvailability;
    }

    public void setEndDateAvailability(LocalDate endDateAvailability) {
        this.endDateAvailability = endDateAvailability;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

} 