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
    }
}
