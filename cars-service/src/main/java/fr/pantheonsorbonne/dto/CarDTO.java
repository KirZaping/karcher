package fr.pantheonsorbonne.dto;

public class CarDTO {
    //id
    //type
    //brand
    //model
    //location
    //startDateAvailability
    //endDateAvailability
    // startDate
    // endDate
    // nbOfDays
    // pricePerDay
    // totalPrice"
    private String model;
    private String brand;
    private double price;

    // Getters et Setters
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
} 