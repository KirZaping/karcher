package fr.pantheonsorbonne.gateway;

import java.time.LocalDate;

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
                String task = exchange.getIn().getHeader("task", String.class);
                if ("available".equals(task)) {
                    exchange.getIn().setBody(carService.getAvailableCars(
                        exchange.getIn().getHeader("location", String.class),
                        LocalDate.parse(exchange.getIn().getHeader("startDate", String.class)),
                        LocalDate.parse(exchange.getIn().getHeader("endDate", String.class))
                    ));
                } else {
                    exchange.getIn().setBody(carService.getAllCars());
                }
                exchange.getIn().setHeader("Content-Type", "application/json");
            });
    }
}
