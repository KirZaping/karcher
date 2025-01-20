package fr.pantheonsorbonne.service;

import java.time.LocalDate;
//import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.pantheonsorbonne.model.Car;
import fr.pantheonsorbonne.repository.CarRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CarService {

    @Inject
    CarRepository carRepository;

    @Transactional
    public String getAllCars() {
        String response = "[" + carRepository.listAll().stream()
            .map(car -> String.format("{\"carId\": \"%s\", \"type\": \"%s\", \"brand\": \"%s\", \"model\": \"%s\"}", 
                car.getId(), car.getType(), car.getBrand(), car.getModel()))
            .collect(Collectors.joining(", ")) + "]";
        return response; // Retourne la liste des voitures au format JSON
    }

    @Transactional
    public String getAvailableCars(String location, LocalDate startDate, LocalDate endDate) {
        List<Car> availableCars = carRepository.findAvailableCars(location, startDate, endDate);
        String response = "[" + availableCars.stream()
            .map(car -> String.format("{\"carId\": \"%s\", \"type\": \"%s\", \"brand\": \"%s\", \"model\": \"%s\", \"location\": \"%s\", \"startDate\": \"%s\", \"endDate\": \"%s\"}", 
                car.getId(), car.getType(), car.getBrand(), car.getModel(), location, startDate, endDate))
            .collect(Collectors.joining(", ")) + "]";
        return response; // Retourne la liste des voitures disponibles au format JSON
    }
} 