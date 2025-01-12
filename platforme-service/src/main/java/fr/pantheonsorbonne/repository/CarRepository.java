package fr.pantheonsorbonne.repository;

import fr.pantheonsorbonne.model.Car;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CarRepository implements PanacheRepository<Car> {
    // You can add custom methods here if needed
} 

// package fr.pantheonsorbonne.repository;

// import java.util.List;

// import fr.pantheonsorbonne.model.Car;
// import jakarta.enterprise.context.ApplicationScoped;
// import jakarta.persistence.EntityManager;
// import jakarta.persistence.PersistenceContext;

// @ApplicationScoped
// public class CarRepository {

//     @PersistenceContext
//     private EntityManager entityManager;

//     public void persist(Car car) {
//         entityManager.persist(car);
//     }

//     public List<Car> listAll() {
//         return entityManager.createQuery("SELECT c FROM Car c", Car.class).getResultList();
//     }
// }