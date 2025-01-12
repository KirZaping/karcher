package fr.pantheonsorbonne.repository;

import fr.pantheonsorbonne.model.CarAvailability;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class CarAvailabilityRepository implements PanacheRepository<CarAvailability> {

    /**
     * Find all cars that are available in a specific location within a given date range.
     *
     * @param location  the location to filter by
     * @param startDate the start date of the desired rental period
     * @param endDate   the end date of the desired rental period
     * @return a list of CarAvailability objects matching the criteria
     */
    public List<CarAvailability> findAvailableCars(String location, LocalDate startDate, LocalDate endDate) {
        return find(
                "car.location = ?1 and startDate <= ?2 and endDate >= ?3",
                location, startDate, endDate
        ).list();
    }
}
