package fr.pantheonsorbonne.dto;

import java.time.LocalDate;

public class CarAvailabilityRequestDTO {
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;

    // Constructors
    public CarAvailabilityRequestDTO() {}

    public CarAvailabilityRequestDTO(String location, LocalDate startDate, LocalDate endDate) {
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
