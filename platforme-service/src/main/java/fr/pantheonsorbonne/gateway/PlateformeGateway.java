package fr.pantheonsorbonne.gateway;

import org.apache.camel.builder.RouteBuilder;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlateformeGateway extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        // Route pour récupérer des données JSON de l'API assurance-service
        rest("/fetch-assurance")
            .get()
            .to("direct:fetch-assurance");

        from("direct:fetch-assurance")
            .to("http://localhost:8080/assurance?type=Arnaque&age=25&duree_permis=50&bridgeEndpoint=true")
            .log("Réponse du service assurance: ${body}");

    }
} 