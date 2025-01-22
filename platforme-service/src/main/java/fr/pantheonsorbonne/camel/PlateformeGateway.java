package fr.pantheonsorbonne.camel;

import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import fr.pantheonsorbonne.camel.processors.AssuranceSelectedProcessor;
import fr.pantheonsorbonne.camel.processors.ChooseCarProcessor;
import fr.pantheonsorbonne.camel.processors.ConfirmLocationProcessor;
import fr.pantheonsorbonne.camel.processors.FetchAssuranceProcessor;
import fr.pantheonsorbonne.camel.processors.FetchCarProcessor;
import fr.pantheonsorbonne.camel.processors.LenderAskingLocation;
import fr.pantheonsorbonne.camel.processors.LenderRejectProcessor;
import fr.pantheonsorbonne.camel.processors.SetAssurancePriceProcessor;
import fr.pantheonsorbonne.service.PlatformService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PlateformeGateway extends RouteBuilder{

    @ConfigProperty(name = "quarkus.artemis.queue.platformeQueue")
    String platformeQueue;

    @ConfigProperty(name = "quarkus.artemis.queue.assuranceQueue")
    String assuranceQueue;

    @ConfigProperty(name = "quarkus.artemis.queue.carsQueue")
    String carsQueue;

    @ConfigProperty(name = "quarkus.artemis.queue.lenderQueue")
    String lenderQueue;

    @Inject
    PlatformService platformService;

    @Override
    public void configure() throws Exception {
        restConfiguration()
            .host("localhost")
            .port(24000);

        // http://localhost:24000/fetch-assurance?type=Arnaque&age=200&duree_permis=5
        from("rest:get:/fetch-assurance")
            .process(new FetchAssuranceProcessor())
            .to(assuranceQueue)
            .log("${body}")
            .process(new SetAssurancePriceProcessor(platformService));

        // http://localhost:24000/choose-car?task=confirm-location?carId=4
        from("rest:get:/choose-car")
            .log("[PlateformeGateway] Message en cours de traitement")
            .process(new ConfirmLocationProcessor())
            .to(carsQueue);


        // http://localhost:24000/fetch-car?task=available&location=Paris&startDate=2025-01-23&endDate=2025-01-25
        from("rest:get:/fetch-car")
            .log("[PlateformeGateway] Message en cours de traitement")
            .process(new FetchCarProcessor())
            .to(carsQueue);

        // http://localhost:24000/confirm-location?carId=4&startDate=2025-01-23&endDate=2025-01-25
        from("rest:get:/confirm-location")
            //.process(new AssuranceSelectedProcessor(platformService))
            .to(lenderQueue)
            .log("[PlateformeGateway] Message reçu du broker")
            .process(new LenderAskingLocation())
            .choice()
                .when().simple("${body} contains 'accept'")
                    .process(exchange -> {
                        String carId = exchange.getIn().getHeader("carId", String.class);
                        exchange.getIn().setBody("{\"carid\": \"" + carId + "\"}");
                    })
                    .to("direct:chooseCar")
                .otherwise()
                    .process(exchange -> {
                        String carId = exchange.getIn().getHeader("carId", String.class);
                        exchange.getIn().setBody("{\"carid\": \"" + carId + "\"}");
                    })
                    .to("direct:lender-reject");
            
        from("direct:chooseCar")
            .process(new ChooseCarProcessor())
            .to(carsQueue);
    
        from("direct:lender-reject")
            .process(new LenderRejectProcessor());
    }
} 
