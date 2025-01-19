package fr.pantheonsorbonne.service;

import java.time.LocalDate;
//import java.util.ArrayList;
import java.util.List;

import fr.pantheonsorbonne.model.Car;
import fr.pantheonsorbonne.repository.CarRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CarService {

    @Inject
    CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.listAll(); // Retrieve all cars from the database
    }

    public List<Car> getAvailableCars(String location, LocalDate startDate, LocalDate endDate) {
        return carRepository.findAvailableCars(location, startDate, endDate);
    }



    // public List<Car> listCars() {
    //     return carRepository.listAll(); // Retrieve all cars available in the database
    // }

    // public String fetchCarAvailability(String carId, String startDate, String endDate) {
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