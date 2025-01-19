package fr.pantheonsorbonne.repository;

import fr.pantheonsorbonne.model.Car;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class CarRepository implements PanacheRepository<Car> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire

    public List<Car> findAvailableCars(String location, LocalDate startDate, LocalDate endDate) {
        //return find("startDateAvailability <= ?1 and endDateAvailability >= ?2", startDate, endDate).list();
    return listAll();
    }
} 