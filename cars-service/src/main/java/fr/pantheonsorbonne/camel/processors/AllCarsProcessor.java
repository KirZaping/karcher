package fr.pantheonsorbonne.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import fr.pantheonsorbonne.service.CarService;
import io.vertx.core.json.JsonObject;

public class AllCarsProcessor implements Processor {

    private final CarService carService;

    public AllCarsProcessor(CarService carService) {
        this.carService = carService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        if (carService == null) {
            JsonObject errorResponse = new JsonObject();
            errorResponse.put("error", "CarService is not initialized");
            exchange.getIn().setBody(errorResponse.toString());
            exchange.getIn().setHeader("CamelHttpResponseCode", 500);
            exchange.getIn().setHeader("Content-Type", "application/json");
            return;
        }

        String response = carService.getAllCars();
        exchange.getIn().setBody(response);
        exchange.getIn().setHeader("Content-Type", "application/json");
    }
}
