package fr.pantheonsorbonne.gateway;

import java.time.LocalDate;

import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import fr.pantheonsorbonne.service.CarService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CarGateway extends RouteBuilder{

    @Inject
    CarService carService;

    @ConfigProperty(name = "quarkus.artemis.queue.carsQueue")
    String carsQueue;

    @Override
    public void configure() throws Exception {

        from(carsQueue)
            .log("[CarGateway] Message reçu du broker: ${body}")
            .process(exchange -> {
                String task = exchange.getIn().getHeader("task", String.class);
                if ("available".equals(task)) {
                    exchange.getIn().setBody(carService.getAvailableCars(
                        exchange.getIn().getHeader("location", String.class),
                        LocalDate.parse(exchange.getIn().getHeader("startDate", String.class)),
                        LocalDate.parse(exchange.getIn().getHeader("endDate", String.class))
                    ));
                } else if ("choose-car".equals(task)) {
                    String carId = exchange.getIn().getHeader("carId", String.class);
                    exchange.getIn().setBody("{\"status\": \"Car choosing\", \"carId\": \"" + carId + "\"}");
                    //exchange.getIn().setBody(carService.chooseCar(exchange.getIn().getHeader("carId", String.class)));
                } else {
                    exchange.getIn().setBody(carService.getAllCars());
                }
                exchange.getIn().setHeader("Content-Type", "application/json");
            })
            .log("[CarGateway] Réponse du service de disponibilité des voitures: ${body}");
    }
}
