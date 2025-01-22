package fr.pantheonsorbonne.camel;

import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import fr.pantheonsorbonne.camel.processors.LenderConfirmation;
import fr.pantheonsorbonne.service.LenderService;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LenderGateway extends RouteBuilder {

    @ConfigProperty(name = "quarkus.artemis.queue.lenderQueue")
    String lenderQueue;

    @Inject
    LenderService lenderService;

    @Override
    public void configure() throws Exception {

        from(lenderQueue)
            .log("[LenderGateway] Message reçu du broker: ${body}")
            .process(new LenderConfirmation(lenderService))
            .log("Réponse du service prêteur: ${body}");
    }
}
