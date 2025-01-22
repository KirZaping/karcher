package fr.pantheonsorbonne.repository;
import fr.pantheonsorbonne.model.CarReservation;

import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped

public class CarReservationRepository implements PanacheRepository<CarReservation> {
    
}
