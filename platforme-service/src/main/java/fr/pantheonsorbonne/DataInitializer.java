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
        System.out.println("Adding default cars");
        addDefaultCars();
    }

    @Transactional
    public void addDefaultCars() {
        try {
            Random random = new Random();
            for (int i = 1; i <= 10; i++) { // Change to 10 cars
                Car car = new Car();
                car.setType(random.nextBoolean() ? "SUV" : "Sedan"); // Randomly choose between SUV and Sedan
                car.setBrand(random.nextBoolean() ? "Toyota" : "BMW"); // Randomly choose between Toyota and BMW
                car.setModel("Model " + (random.nextInt(100) + 1)); // Random model number between 1 and 100
                car.setOwner("Owner " + i);
                car.setPricePerDay(50 + (random.nextDouble() * 50)); // Random price between 50 and 100
                //LocalDate startDate = LocalDate.now().plusDays(random.nextInt(1)); // Random start date within 30 days
                //car.setStartDateAvailability(startDate); 
                //car.setEndDateAvailability(startDate.plusDays(10)); // Fixed duration of availability for 10 days
                
                car.setStartDateAvailability(LocalDate.now());
                car.setEndDateAvailability(LocalDate.now().plusDays(10));
                // Set location alternating between Paris and Beirut
                car.setLocation(i % 2 == 0 ? "Beirut" : "Paris");

                // Ensure price is set before persisting
                if (car.getPricePerDay() <= 0) {
                    throw new IllegalArgumentException("Price per day must be greater than zero.");
                }

                carRepository.persist(car);
            }
            
            System.out.println("10 random cars added.");
        } catch (PersistenceException e) {
            System.err.println("Error adding default vehicles: " + e.getMessage());
        }
    }
}