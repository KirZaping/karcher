package fr.pantheonsorbonne.camel;

import org.apache.camel.builder.RouteBuilder;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CamelRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("rest:get:/assurance") // Intercepte les requêtes GET à /assurance
            .setBody(constant("{\"info\": \"Information sur l'assurance\"}")) // Définit la réponse
            .setHeader("Content-Type", constant("application/json")); // Définit le type de contenu
    }
}
