package fr.pantheonsorbonne.camel.processors;

import java.time.LocalDate;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import jakarta.inject.Inject;
import fr.pantheonsorbonne.service.CarService;

public class CarsManagerProcessor implements Processor {

    @Inject
    CarService carService;

    @Override
    public void process(Exchange exchange) throws Exception {
        String task = exchange.getIn().getHeader("task", String.class);
        if ("available".equals(task)) {
            exchange.getIn().setBody(carService.getAvailableCars(
                exchange.getIn().getHeader("location", String.class),
                LocalDate.parse(exchange.getIn().getHeader("startDate", String.class)),
                LocalDate.parse(exchange.getIn().getHeader("endDate", String.class))
            ));
            
        // } else if ("reserve".equals(task)) {
        //     exchange.getIn().setBody(carService.reserveCar(
        //         Long.parseLong(exchange.getIn().getHeader("carId", String.class)),
        //         LocalDate.parse(exchange.getIn().getHeader("startDate", String.class)),
        //         LocalDate.parse(exchange.getIn().getHeader("endDate", String.class))
        //     ));
        } else {
            exchange.getIn().setBody(carService.getAllCars());
        }
        exchange.getIn().setHeader("Content-Type", "application/json");
    }
}
