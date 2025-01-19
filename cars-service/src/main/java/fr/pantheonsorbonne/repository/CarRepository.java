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
} 