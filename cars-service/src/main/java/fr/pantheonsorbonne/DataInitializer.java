package fr.pantheonsorbonne;

import fr.pantheonsorbonne.model.Car;
import fr.pantheonsorbonne.repository.CarRepository;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

import java.util.Random;
import java.time.LocalDate;

@ApplicationScoped
public class DataInitializer {

    @Inject
    CarRepository carRepository;

    public void onStart(@Observes StartupEvent ev) {
        System.out.println("Resetting and adding default cars");
        resetDataset(); // Clear existing data
        addDefaultCars(); // Add new data
    }

    @Transactional
    public void resetDataset() {
        try {
            // Clear existing cars from the database
            carRepository.deleteAllCars(); // Call the method to delete all cars
            System.out.println("Existing cars have been deleted.");
        } catch (PersistenceException e) {
            System.err.println("Error resetting dataset: " + e.getMessage());
        }
    }

    @Transactional
    public void addDefaultCars() {
        try {
            // Clear existing cars from the database before adding new ones
            resetDataset(); // Ensure the table is cleared before adding new cars
            
            Random random = new Random();
            for (int i = 1; i <= 15; i++) { // Change to 15 cars
                Car car = new Car();
                car.setType(random.nextBoolean() ? "SUV" : "Sedan"); // Randomly choose between SUV and Sedan
                car.setBrand(random.nextBoolean() ? "Toyota" : "BMW"); // Randomly choose between Toyota and BMW
                car.setModel("Model " + (random.nextInt(100) + 1)); // Random model number between 1 and 100
                car.setOwner("Owner " + i);
                car.setPricePerDay(50 + random.nextInt(51)); // Random price between 50 and 100 (int)
                
                car.setStartDateAvailability(LocalDate.now()); // Fixed start date (today)
                car.setEndDateAvailability(LocalDate.now().plusDays(random.nextInt(10) + 1)); // Varying end dates (1 to 10 days from today)
                
                // Set location alternating between Paris and Beirut
                car.setLocation(i % 2 == 0 ? "Beirut" : "Paris");

                // Ensure price is set before persisting
                if (car.getPricePerDay() <= 0) {
                    throw new IllegalArgumentException("Price per day must be greater than zero.");
                }

                carRepository.persist(car);
            }
            
            System.out.println("15 random cars added.");
        } catch (PersistenceException e) {
            System.err.println("Error adding default vehicles: " + e.getMessage());
        }
    }
}