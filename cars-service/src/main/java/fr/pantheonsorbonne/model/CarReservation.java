package fr.pantheonsorbonne.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CarReservation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId; // Unique identifier for the reservation

    private Long carId; // ID of the reserved car
    private LocalDate startReservationDate; // Start date of the reservation
    private LocalDate endReservationDate; // End date of the reservation

    // Default constructor
    public CarReservation() {
        // Necessary for Hibernate
    }

    // Constructor with parameters
    public CarReservation(Long carId, LocalDate startReservationDate, LocalDate endReservationDate) {
        this.carId = carId;
        this.startReservationDate = startReservationDate;
        this.endReservationDate = endReservationDate;
    }

    // Getters and Setters
    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public LocalDate getStartReservationDate() {
        return startReservationDate;
    }

    public void setStartReservationDate(LocalDate startReservationDate) {
        this.startReservationDate = startReservationDate;
    }

    public LocalDate getEndReservationDate() {
        return endReservationDate;
    }

    public void setEndReservationDate(LocalDate endReservationDate) {
        this.endReservationDate = endReservationDate;
    }
}
