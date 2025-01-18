package fr.pantheonsorbonne.service;

import java.util.List;

import fr.pantheonsorbonne.model.Car;
import fr.pantheonsorbonne.repository.CarRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CarService {

    @Inject
    CarRepository carRepository;

    public List<Car> listAllCars() {
        return carRepository.listAll();
    }

    public void addCar(Car car) {
        if (car.getPricePerDay() < 0) {
            throw new IllegalArgumentException("Le prix ne peut pas être négatif.");
        }
        try {
            carRepository.persist(car);
        } catch (Exception e) {
            System.err.println("Erreur lors de l'ajout de la voiture : " + e.getMessage());
            throw new RuntimeException("Erreur lors de l'ajout du véhicule. Vérifiez les informations saisies.");
        }
    }

    public void addDefaultCar() {
        Car car = new Car();
        car.setType("SUV");
        car.setBrand("Toyota");
        car.setModel("RAV4");
        car.setOwner("Jean Dupont");
        car.setPricePerDay(50);
        car.setInsurance("Tous risques");
        car.setImage("http://localhost:8080/images/car1.png");

        addCar(car);
    }
} 