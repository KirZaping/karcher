package fr.pantheonsorbonne.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelRestConfig extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // REST Configuration
        restConfiguration()
                .component("rest")
                .host("localhost")
                .port(8080);

        // REST Endpoint for Car Availability
        rest("/cars")
                .post("/availability")
                .consumes("application/json")
                .produces("application/json")
                .to("direct:getAvailableCars");
    }
}
