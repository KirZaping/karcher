package fr.pantheonsorbonne.gateway;

import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import fr.pantheonsorbonne.service.LenderService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class LenderGateway extends RouteBuilder {

    @Inject
    private LenderService lenderService;

    @ConfigProperty(name = "quarkus.artemis.queue.lenderQueue")
    String lenderQueue;

    @Override
    public void configure() throws Exception {

        from(lenderQueue)
            .log("[LenderGateway] Message reçu du broker: ${body}")
            .process(exchange -> {
                String response = lenderService.lenderConfirmation();
                exchange.getIn().setBody(response);
            })
            .log("Réponse du service prêteur: ${body}");


    }
}
