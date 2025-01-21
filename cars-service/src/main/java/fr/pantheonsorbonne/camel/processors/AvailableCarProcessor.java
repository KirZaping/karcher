package fr.pantheonsorbonne.camel.processors;

import java.time.LocalDate;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import fr.pantheonsorbonne.service.CarService;

public class AvailableCarProcessor implements Processor {

    private final CarService carService;

    public AvailableCarProcessor(CarService carService) {
        this.carService = carService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        
        exchange.getIn().setBody(carService.getAvailableCars(
            exchange.getIn().getHeader("location", String.class),
            LocalDate.parse(exchange.getIn().getHeader("startDate", String.class)),
            LocalDate.parse(exchange.getIn().getHeader("endDate", String.class))
        ));
        exchange.getIn().setHeader("Content-Type", "application/json");
    }
}
