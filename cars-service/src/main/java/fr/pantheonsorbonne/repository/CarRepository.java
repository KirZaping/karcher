package fr.pantheonsorbonne.repository;

import java.time.LocalDate;
import java.util.List;

import fr.pantheonsorbonne.model.Car;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CarRepository implements PanacheRepository<Car> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
    
    public List<Car> findAvailableCars(String location, LocalDate startDate, LocalDate endDate) {
        return find("location = ?1 and startDateAvailability <= ?2 and endDateAvailability >= ?3", location, startDate, endDate).list();
    }

    public List<Car> findAllCars() {
        return listAll(); // Retrieve the list of cars directly from the MariaDB database
    }

    // Method to delete all cars from the database
    public void deleteAllCars() {
        deleteAll(); // This will delete all records in the Car table
    }


    public Car getCar(Long carId){
        Car car=findById(carId);
        return car;
    }

    // Method to reserve a car by updating its availability dates
    // public void reserveCar(Long carId, LocalDate startDate, LocalDate endDate) {
    //     Car car = findById(carId);
    //     if (car != null) {
    //         boolean isOverlapping = startDate.isBefore(car.getEndDateAvailability()) && endDate.isAfter(car.getStartDateAvailability());
    //         if (isOverlapping) {
    //             LocalDate newStartDate = startDate.isAfter(car.getStartDateAvailability()) ? startDate : car.getStartDateAvailability();
    //             LocalDate newEndDate = endDate.isBefore(car.getEndDateAvailability()) ? endDate : car.getEndDateAvailability();
    //             car.setStartDateAvailability(newStartDate);
    //             car.setEndDateAvailability(newEndDate);
    //         }
    //         persist(car); // Save the updated car back to the database
    //     }

    }
 