package fr.pantheonsorbonne.camel;

import org.apache.camel.builder.RouteBuilder;

public class CamelRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // Route pour récupérer des données JSON de l'API assurance-service
        from("timer://foo?repeatCount=1")
            .to("http://localhost:8080/assurance")
            .log("Réponse du service assurance: ${body}");
    }
}
