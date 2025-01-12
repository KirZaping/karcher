package fr.pantheonsorbonne.dto;

public class CarAvailabilityResponseDTO {
    private Long carId;
    private String make;

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
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

    public double getPriceEstimation() {
        return priceEstimation;
    }

    public void setPriceEstimation(double priceEstimation) {
        this.priceEstimation = priceEstimation;
    }

    private String model;
    private double priceEstimation;
}
