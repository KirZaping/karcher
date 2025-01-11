package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.model.Car;
import fr.pantheonsorbonne.repository.CarRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class CarService {

    @Inject
    CarRepository carRepository;

    public List<Car> listAllCars() {
        return carRepository.listAll();
    }

    public void addCar(Car car) {
        carRepository.persist(car);
    }
} 