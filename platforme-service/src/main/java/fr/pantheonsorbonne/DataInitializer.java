package fr.pantheonsorbonne;

import fr.pantheonsorbonne.model.Car;
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
            for (int i = 1; i <= 50; i++) {
                Car car = new Car();
                car.setType(i % 2 == 0 ? "SUV" : "Berline"); // Alternance entre SUV et Berline
                car.setBrand(i % 2 == 0 ? "Toyota" : "BMW"); // Alternance entre Toyota et BMW
                car.setModel("Modèle " + i);
                car.setOwner("Propriétaire " + i);
                car.setPrice(50 + (i * 2)); // Prix croissant
                car.setInsurance("Tous risques");
                car.setImage("../images/car" + i + ".png");

                carRepository.persist(car);
            }
            
            System.out.println("50 voitures par défaut ajoutées.");
        } catch (PersistenceException e) {
            System.err.println("Erreur lors de l'ajout des véhicules par défaut : " + e.getMessage());
        }
    }
}