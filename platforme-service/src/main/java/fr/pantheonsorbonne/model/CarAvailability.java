package fr.pantheonsorbonne.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "car_availability")
public class CarAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    // Default constructor (required by JPA)
    public CarAvailability() {}

    // Custom constructor for easier instantiation
    public CarAvailability(Car car, LocalDate startDate, LocalDate endDate) {
        this.car = car;
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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

    @Override
    public String toString() {
        return "CarAvailability{" +
                "id=" + id +
                ", car=" + car +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
