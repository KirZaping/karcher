package fr.pantheonsorbonne.model;

import java.time.LocalDate;

public class Booking {
    private Long id;
    private Long userId;
    private Long carId;
    private LocalDate startDate;
    private LocalDate endDate;

    // Constructors
    public Booking() {}

    public Booking(Long id, Long userId, Long carId, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
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
