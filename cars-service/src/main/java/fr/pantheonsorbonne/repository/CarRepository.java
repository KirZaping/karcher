package fr.pantheonsorbonne.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.pantheonsorbonne.model.Car;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CarRepository implements PanacheRepository<Car> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire

    public List<Car> findAvailableCars(String location, LocalDate startDate, LocalDate endDate) {
        //return find("startDateAvailability <= ?1 and endDateAvailability >= ?2", startDate, endDate).list();
        return listAll();
    }

    public String findAllCars() {
        List<Car> cars = generateFakeCars(); // Génère une liste de voitures factices

        if (cars.isEmpty()) {
            return "{\"error\": \"no data found\"}";
        }

        StringBuilder result = new StringBuilder("[");

        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            if (i > 0) {
                result.append(", ");
            }
            result.append(String.format("{\"id\": \"%s\", \"brand\": \"%s\", \"model\": \"%s\", \"pricePerDay\": %.2f, \"location\": \"%s\"}", 
                car.getId(), car.getBrand(), car.getModel(), car.getPricePerDay(), car.getLocation()));
        }
        result.append("]");
        
        return result.toString();
    }

    public List<Car> generateFakeCars() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(1L, "Owner1", "Toyota", "RAV4", 50.0, LocalDate.now().minusDays(1), LocalDate.now().plusDays(10), "SUV", "Location1"));
        cars.add(new Car(2L, "Owner2", "Honda", "Civic", 40.0, LocalDate.now().minusDays(5), LocalDate.now().plusDays(15), "Sedan", "Location2"));
        cars.add(new Car(3L, "Owner3", "Ford", "Focus", 30.0, LocalDate.now().minusDays(5), LocalDate.now().plusDays(5), "Hatchback", "Location3"));
        cars.add(new Car(4L, "Owner4", "BMW", "Z4", 70.0, LocalDate.now().minusDays(2), LocalDate.now().plusDays(12), "Convertible", "Location4"));
        cars.add(new Car(5L, "Owner5", "Audi", "A5", 60.0, LocalDate.now().minusDays(3), LocalDate.now().plusDays(3), "Coupe", "Location5"));
        return cars;
    }
} 