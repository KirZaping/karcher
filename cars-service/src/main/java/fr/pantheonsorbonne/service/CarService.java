package fr.pantheonsorbonne.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.pantheonsorbonne.model.Car;
import fr.pantheonsorbonne.repository.CarRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CarService {

    @Inject
    CarRepository carRepository;

    public String getAllCars() {
        return carRepository.findAllCars(); // Récupère toutes les voitures de la base de données
    }

    public String listCars() {
        String cars = carRepository.findAllCars(); // Récupère toutes les voitures disponibles dans la base de données
        return cars;
    }


    public String fetchCarAvailability(String carId, String startDate, String endDate) {
        // Récupérer les voitures factices
        List<Car> cars = carRepository.generateFakeCars();
        List<Car> availableCars = new ArrayList<>();

        // Vérifier les dates de disponibilité
        for (Car car : cars) {
            String current_id = car.getId() != null ? car.getId().toString() : null;

            // Check for null before dereferencing
            if (current_id != null && carId != null && current_id.equals(carId) && 
                (car.getStartDateAvailability().isBefore(LocalDate.parse(endDate)) && // La date de début de disponibilité est avant la date de fin de réservation
                 car.getEndDateAvailability().isAfter(LocalDate.parse(startDate)) && // La date de fin de disponibilité est après la date de début de réservation
                 !(car.getStartDateAvailability().isAfter(LocalDate.parse(endDate)) || // La date de début de disponibilité ne doit pas être après la date de fin de réservation
                   car.getEndDateAvailability().isBefore(LocalDate.parse(startDate))))) { // La date de fin de disponibilité ne doit pas être avant la date de début de réservation
                availableCars.add(car);
                System.out.println("[DEBUG] - voiture ajoutée:" + car.getStartDateAvailability() + " " + car.getEndDateAvailability() + " " + carId + " " + car.getId() + " " + LocalDate.parse(startDate) + " " + LocalDate.parse(endDate));
            }
        }

        if (availableCars.isEmpty()) {
            return "{\"Empty\": \"no available cars found\"}";
        }

        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < availableCars.size(); i++) {
            Car car = availableCars.get(i);
            if (i > 0) {
                result.append(", ");
            }
            result.append(String.format("{\"carId\": \"%s\", \"type\": \"%s\", \"brand\": \"%s\", \"model\": \"%s\", \"status\": \"%s\"}", 
                car.getId(), car.getType(), car.getBrand(), car.getModel(), "available"));
        }
        result.append("]");
        return result.toString();
    }
} 