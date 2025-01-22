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
import io.vertx.core.json.JsonObject;

@ApplicationScoped
public class CarService {

    @Inject
    CarRepository carRepository;

    @Transactional
    public String getAllCars() {
        List<Car> cars = carRepository.listAll();
        List<JsonObject> jsonCars = cars.stream()
            .map(car -> {
                JsonObject jsonObject = new JsonObject();
                jsonObject.put("carId", car.getId());
                jsonObject.put("owner", car.getOwner());
                jsonObject.put("type", car.getType());
                jsonObject.put("brand", car.getBrand());
                jsonObject.put("model", car.getModel());
                jsonObject.put("pricePerDay", car.getPricePerDay());
                jsonObject.put("startDateAvailability", car.getStartDateAvailability());
                jsonObject.put("endDateAvailability", car.getEndDateAvailability());
                jsonObject.put("location", car.getLocation());
                return jsonObject;
            })
            .collect(Collectors.toList());
        return jsonCars.toString();
    }

    @Transactional
    public String getAvailableCars(String location, LocalDate startDate, LocalDate endDate) {
        List<Car> availableCars = carRepository.findAvailableCars(location, startDate, endDate);
        long nbOfDays = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
        List<JsonObject> jsonCars = availableCars.stream()
            .map(car -> {
                    JsonObject jsonObject = new JsonObject();
                        jsonObject.put("carId", car.getId());
                        jsonObject.put("type", car.getType());
                        jsonObject.put("brand", car.getBrand());
                        jsonObject.put("model", car.getModel());
                        jsonObject.put("location", car.getLocation());
                        jsonObject.put("startDateAvailability", car.getStartDateAvailability());
                        jsonObject.put("endDateAvailability", car.getEndDateAvailability());
                        jsonObject.put("startDate", startDate);
                        jsonObject.put("endDate", endDate);
                        jsonObject.put("nbOfDays", nbOfDays);
                        jsonObject.put("pricePerDay", car.getPricePerDay());
                        jsonObject.put("totalPrice", car.getPricePerDay() * (int)nbOfDays);
                        return jsonObject;
                    })
                .collect(Collectors.toList());
        return jsonCars.toString();
    }

    @Transactional
    public String reserveCar(Long carId, LocalDate startDate, LocalDate endDate) {
        Car selectedCar = carRepository.getCar(carId);
        long nbOfDays = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
        int totalPrice=  selectedCar.getPricePerDay() * (int)nbOfDays;
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("carId", selectedCar.getId());
        jsonObject.put("type", selectedCar.getType());
        jsonObject.put("brand", selectedCar.getBrand());
        jsonObject.put("model", selectedCar.getModel());
        jsonObject.put("location", selectedCar.getLocation());
        jsonObject.put("startDate", startDate);
        jsonObject.put("endDate", endDate);
        jsonObject.put("totalPrice", totalPrice);
        return jsonObject.toString();
    }
} 