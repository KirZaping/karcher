package fr.pantheonsorbonne.service;

import java.time.LocalDate;
//import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.pantheonsorbonne.model.Car;
import fr.pantheonsorbonne.repository.CarRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CarService {

    @Inject
    CarRepository carRepository;

    public String getAllCars() {
        String response = "[" + carRepository.listAll().stream()
            .map(car -> String.format("{\"carId\": \"%s\", \"type\": \"%s\", \"brand\": \"%s\", \"model\": \"%s\"}", 
                car.getId(), car.getType(), car.getBrand(), car.getModel()))
            .collect(Collectors.joining(", ")) + "]";
        return response; // Retourne la liste des voitures au format JSON
    }

    public List<Car> getAvailableCars(String location, LocalDate startDate, LocalDate endDate) {
        return carRepository.findAvailableCars(location, startDate, endDate);
    }



    public String listCars() {
        List<Car> cars = carRepository.findAllCars();
        String response = "[" + cars.stream()
            .map(car -> String.format("{\"carId\": \"%s\", \"type\": \"%s\", \"brand\": \"%s\", \"model\": \"%s\"}", 
                car.getId(), car.getType(), car.getBrand(), car.getModel()))
            .collect(Collectors.joining(", ")) + "]";
        return response;
    }
    //     List<Car> cars = carRepository.findAvailableCars("any location", LocalDate.parse(startDate), LocalDate.parse(endDate));
    //     List<Car> availableCars = new ArrayList<>();

    //     for (Car car : cars) {
    //         String current_id = car.getId() != null ? car.getId().toString() : null;

    //         if (current_id != null && carId != null && current_id.equals(carId)) {
    //             availableCars.add(car);
    //             System.out.println("[DEBUG] - Car added: " + car.getStartDateAvailability() + " " + car.getEndDateAvailability() + " " + carId + " " + car.getId() + " " + LocalDate.parse(startDate) + " " + LocalDate.parse(endDate));
    //         }
    //     }

    //     if (availableCars.isEmpty()) {
    //         return "{\"Empty\": \"no available cars found\"}";
    //     }

    //     StringBuilder result = new StringBuilder("[");
    //     for (int i = 0; i < availableCars.size(); i++) {
    //         Car car = availableCars.get(i);
    //         if (i > 0) {
    //             result.append(", ");
    //         }
    //         result.append(String.format("{\"carId\": \"%s\", \"type\": \"%s\", \"brand\": \"%s\", \"model\": \"%s\", \"status\": \"%s\"}", 
    //             car.getId(), car.getType(), car.getBrand(), car.getModel(), "available"));
    //     }
    //     result.append("]");
    //     return result.toString();
    // }
} 