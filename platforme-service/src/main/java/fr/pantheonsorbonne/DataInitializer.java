package fr.pantheonsorbonne;

import fr.pantheonsorbonne.model.Car;
import fr.pantheonsorbonne.model.CarAvailability;
import fr.pantheonsorbonne.repository.CarRepository;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DataInitializer {

    @Inject
    CarRepository carRepository;

    public void onStart(@Observes StartupEvent ev) {
        // Vérifiez si la base de données est vide avant d'ajouter des données
        if (carRepository.count() == 0) {
            addDefaultCars();
        }
    }

    @Transactional
    public void addDefaultCars() {
        try {
            Car car1 = new Car();
            car1.setType("SUV");
            car1.setBrand("Toyota");
            car1.setModel("RAV4");
            car1.setOwner("Jean Dupont");
            car1.setPrice(50);
            car1.setInsurance("Tous risques");
            car1.setImage("../images/car1.png");

            Car car2 = new Car();
            car2.setType("Berline");
            car2.setBrand("BMW");
            car2.setModel("Série 3");
            car2.setOwner("Marie Curie");
            car2.setPrice(70);
            car2.setInsurance("Tous risques");
            car2.setImage("../images/car2.png");

            carRepository.persist(car1);
            carRepository.persist(car2);

            // Add availability periods for each car
            CarAvailability availability1 = new CarAvailability(car1, LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 31));
            CarAvailability availability2 = new CarAvailability(car2, LocalDate.of(2025, 1, 15), LocalDate.of(2025, 1, 25));

            carAvailabilityRepository.persist(availability1);
            carAvailabilityRepository.persist(availability2);

            logger.info("Default cars and availability added.");
            
            System.out.println("Default cars added: " + car1.getModel() + ", " + car2.getModel());
        } catch (PersistenceException e) {
            System.err.println("Erreur lors de l'ajout des véhicules par défaut : " + e.getMessage());
        }
    }
} 