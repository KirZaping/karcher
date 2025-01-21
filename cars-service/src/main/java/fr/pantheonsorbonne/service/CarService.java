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
            .map(car -> String.format("{\"carId\": \"%s\", \"owner\": \"%s\", \"type\": \"%s\", \"brand\": \"%s\", \"model\": \"%s\", \"pricePerDay\": %d, \"startDateAvailability\": \"%s\", \"endDateAvailability\": \"%s\", \"location\": \"%s\"}", 
                car.getId(), car.getOwner(), car.getType(), car.getBrand(), car.getModel(), car.getPricePerDay(), car.getStartDateAvailability(), car.getEndDateAvailability(), car.getLocation()))
            .collect(Collectors.joining(", ")) + "]";
        return response; // Retourne la liste des voitures au format JSON
    }

    @Transactional
    public String getAvailableCars(String location, LocalDate startDate, LocalDate endDate) {
        List<Car> availableCars = carRepository.findAvailableCars(location, startDate, endDate);
        long nbOfDays = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
        String response = "[" + availableCars.stream()
            .map(car -> String.format("{\"carId\": \"%s\", \"type\": \"%s\", \"brand\": \"%s\", \"model\": \"%s\", \"location\": \"%s\", \"startDateAvailability\": \"%s\", \"endDateAvailability\": \"%s\", \"startDate\": \"%s\", \"endDate\": \"%s\", \"nbOfDays\": %d, \"pricePerDay\": %d, \"totalPrice\": %d}", 
                car.getId(), car.getType(), car.getBrand(), car.getModel(), car.getLocation(), car.getStartDateAvailability(), car.getEndDateAvailability(), startDate, endDate, nbOfDays, car.getPricePerDay(), car.getPricePerDay() * (int)nbOfDays))
            .collect(Collectors.joining(", ")) + "]";
        return response; // Retourne la liste des voitures disponibles au format JSON
    }

    @Transactional
    public String reserveCar(Long carId, LocalDate startDate, LocalDate endDate) {
        Car selectedCar = carRepository.getCar(carId);
        long nbOfDays = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
        int totalPrice=  selectedCar.getPricePerDay() * (int)nbOfDays;
        String response = String.format("{\"carId\": \"%s\", \"type\": \"%s\", \"brand\": \"%s\", \"model\": \"%s\", \"location\": \"%s\", \"startDate\": \"%s\", \"endDate\": \"%s\", \"totalPrice\": %d}", 
            selectedCar.getId(), selectedCar.getType(), selectedCar.getBrand(), selectedCar.getModel(), selectedCar.getLocation(), startDate, endDate, totalPrice);
        return response; // Retourne les détails de la réservation au format JSON

    }
} 