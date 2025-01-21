package fr.pantheonsorbonne.camel;

import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import fr.pantheonsorbonne.camel.processors.LenderConfirmation;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LenderGateway extends RouteBuilder {

    @ConfigProperty(name = "quarkus.artemis.queue.lenderQueue")
    String lenderQueue;

    @Override
    public void configure() throws Exception {

        from(lenderQueue)
            .log("[LenderGateway] Message reçu du broker: ${body}")
            .process(new LenderConfirmation())
            .log("Réponse du service prêteur: ${body}");
    }
}
