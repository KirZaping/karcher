package fr.pantheonsorbonne.camel;

import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import fr.pantheonsorbonne.camel.processors.AssuranceManagerProcessor;
import fr.pantheonsorbonne.service.AssuranceService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AssuranceGateway extends RouteBuilder {

    @Inject
    AssuranceService assuranceService;

    @ConfigProperty(name = "quarkus.artemis.queue.assuranceQueue")
    String assuranceQueue;

    @Override
    public void configure() throws Exception {
        from(assuranceQueue)
            .log("[AssuranceGateway] Message reçu du broker: ${body}")
            .process(new AssuranceManagerProcessor());
    }
} 
