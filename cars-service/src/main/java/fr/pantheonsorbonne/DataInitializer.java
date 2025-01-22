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
        resetDataset();
        addDefaultCars();
    }

    @Transactional
    public void resetDataset() {
        try {
            carRepository.deleteAllCars();
            System.out.println("Existing cars have been deleted.");
        } catch (PersistenceException e) {
            System.err.println("Error resetting dataset: " + e.getMessage());
        }
    }

    @Transactional
    public void addDefaultCars() {
        try {
            resetDataset();
            
            Random random = new Random();
            for (int i = 1; i <= 15; i++) {
                Car car = new Car();
                car.setType(random.nextBoolean() ? "SUV" : "Sedan");
                car.setBrand(random.nextBoolean() ? "Toyota" : "BMW");
                car.setModel("Model " + (random.nextInt(100) + 1));
                car.setOwner("Owner " + i);
                car.setPricePerDay(50 + random.nextInt(51));
                
                car.setStartDateAvailability(LocalDate.now());
                car.setEndDateAvailability(LocalDate.now().plusDays(random.nextInt(10) + 1));
                
                car.setLocation(i % 2 == 0 ? "Beirut" : "Paris");

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