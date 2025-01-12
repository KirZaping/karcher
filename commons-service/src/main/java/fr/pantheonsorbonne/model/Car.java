package fr.pantheonsorbonne.model;

public class Car {
    private Long id;
    private String make;
    private String model;
    private String location;
    private double pricePerDay;

    // Constructors
    public Car() {}

    public Car(Long id, String make, String model, String location, double pricePerDay) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.location = location;
        this.pricePerDay = pricePerDay;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}

