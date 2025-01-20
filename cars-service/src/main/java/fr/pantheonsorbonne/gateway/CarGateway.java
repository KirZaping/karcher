package fr.pantheonsorbonne.gateway;

import org.apache.camel.builder.RouteBuilder;

import fr.pantheonsorbonne.service.CarService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CarGateway extends RouteBuilder{

    @Inject
    CarService carService;

    @Override
    public void configure() throws Exception {

        from("sjms2:queue:cars-queue")
            .log("[CarGateway] Message reçu du broker: ${body}")
            .process(exchange -> {
                exchange.getIn().setBody(carService.getAllCars());
                exchange.getIn().setHeader("Content-Type", "application/json");
            })
            .log("[CarGateway] Réponse du service cars: ${body}");

        /*from("rest:get:/list-availability/fetch-availability-cars")
            .process(exchange -> {
                String response = carService.fetchCarAvailability(exchange.getIn().getHeader("carId", String.class), 
                exchange.getIn().getHeader("startDate", String.class), 
                exchange.getIn().getHeader("endDate", String.class));
                exchange.getIn().setBody(response);
                exchange.getIn().setHeader("Content-Type", "application/json");
                
            })
            .log("Réponse du service cars: ${body}");*/
    }
}
