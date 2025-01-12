package fr.pantheonsorbonne.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // Route to fetch available cars
        from("direct:getAvailableCars")
                .log("Received request: ${body}")
                .setBody(simple("SELECT id, make, model, price_per_day FROM cars WHERE location = '${body.location}'"))
                .to("jdbc:dataSource") // Query the database
                .split(body()) // Process each row in the result set
                .process(exchange -> {
                    var row = exchange.getIn().getBody(Map.class);
                    var response = new CarAvailabilityResponseDTO();
                    response.setCarId((Long) row.get("id"));
                    response.setMake((String) row.get("make"));
                    response.setModel((String) row.get("model"));
                    double pricePerDay = (double) row.get("price_per_day");
                    response.setPriceEstimation(pricePerDay * 3); // Example: Price for 3 days
                    exchange.getIn().setBody(response);
                })
                .end()
                .log("Response ready: ${body}");
    }
}
