package fr.pantheonsorbonne.gateway;

import org.apache.camel.builder.RouteBuilder;

import fr.pantheonsorbonne.service.LenderService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class LenderGateway extends RouteBuilder {

    @Inject
    private LenderService lenderService;

    @Override
    public void configure() throws Exception {
        // Vous pouvez ajouter des routes ici si nécessaire
        // Par exemple, une route pour vérifier la disponibilité des prêts
        from("rest:get:/lenders-availability/fetch-lenders")
            .to("direct:checkAvailability");

        from("direct:checkAvailability")
            .process(exchange -> {
                // Vous pouvez ajouter la logique de vérification de la disponibilité ici
                // Par exemple, vous pourriez appeler un service externe ou une base de données
                exchange.getIn().setBody("{\"status\": \"available\"}");
                String response = lenderService.checkAvailability("1", "2025-01-01", "2025-01-02");
                exchange.getIn().setBody(response);
            })
            .log("Réponse du service prêteur: ${body}");

        //récupérer les informations sur les prêteurs
        from("rest:get:/car-availability")
            .to("direct:car-availability")
            .log("Réponse du service de disponibilité des prêteurs: ${body}")
            .process(exchange -> {
                String response = exchange.getIn().getBody(String.class);
                exchange.getIn().setHeader("Content-Type", "application/json");
                exchange.getIn().setBody(response);
            });

        from("direct:car-availability")
            .process(exchange -> {
                //appeler le service de disponibilité des voitures : http://localhost:8081/car-availability?carId=5&startDate=10/10/10&endDate=12/10/10
                String carId = exchange.getIn().getHeader("carId", String.class);
                String startDate = exchange.getIn().getHeader("startDate", String.class);
                String endDate = exchange.getIn().getHeader("endDate", String.class);
                String availabilityResponse = lenderService.checkAvailability(carId, startDate, endDate);
                exchange.getIn().setBody(availabilityResponse);
                exchange.getIn().setHeader("Content-Type", "application/json");
                exchange.getIn().setBody(availabilityResponse);
            })
            .log("Réponse du service de disponibilité des voitures: ${body}");

        //confirmer la location d'une voiture
        from("rest:post:/confirm-location/confirm-location")
            .to("direct:confirm-location")
            .log("Réponse du service de confirmation de la location: ${body}")
            .process(exchange -> {
                String response = exchange.getIn().getBody(String.class);
                exchange.getIn().setHeader("Content-Type", "application/json");
                exchange.getIn().setBody(response);
            });

        from("direct:confirm-location")
            .process(exchange -> {
                String response = "{coucou : \"coucou\"}";
                //appeler le service de confirmation de la location
                exchange.getIn().setHeader("Content-Type", "application/json");
                exchange.getIn().setBody(response);
            });
    }
}
