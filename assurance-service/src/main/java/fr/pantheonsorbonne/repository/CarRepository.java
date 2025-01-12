package fr.pantheonsorbonne.repository;

import fr.pantheonsorbonne.model.Car;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CarRepository implements PanacheRepository<Car> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
} 