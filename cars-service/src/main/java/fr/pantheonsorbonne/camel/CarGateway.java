package fr.pantheonsorbonne.camel;

import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import fr.pantheonsorbonne.camel.processors.AllCarsProcessor;
import fr.pantheonsorbonne.camel.processors.AvailableCarProcessor;
import fr.pantheonsorbonne.camel.processors.ReserveCarProcessor;
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
            .choice()
                .when().simple("${body} contains 'available'")
                    .to("direct:availableCars")
                .when().simple("${body} contains 'reserve'")
                    .to("direct:reserveCar")
                .otherwise()
                    .to("direct:allCars")
            .log("[CarGateway] Réponse du service de disponibilité des voitures: ${body}");

        from("direct:availableCars")
            .log("[CarGateway] Message reçu du broker: ${body}")
            .process(new AvailableCarProcessor(carService))
            .log("[CarGateway] Réponse du service de disponibilité des voitures: ${body}");

        from("direct:allCars")
            .log("[CarGateway] Message reçu du broker: ${body}")
            .process(new AllCarsProcessor(carService))
            .log("[CarGateway] Réponse du service de disponibilité des voitures: ${body}");

        from("direct:reserveCar")
            .log("[CarGateway] Message reçu du broker: ${body}")
            .process(new ReserveCarProcessor(carService))
            .log("[CarGateway] Réponse du service de disponibilité des voitures: ${body}");
    }
}
