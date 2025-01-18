package fr.pantheonsorbonne.gateway;

import org.apache.camel.builder.RouteBuilder;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlateformeGateway extends RouteBuilder{

    @Override
    public void configure() throws Exception {

        from("rest:get:/fetch-assurance")
            .to("direct:fetch-assurance")
            .log("Réponse du service assurance: ${body}");

        from("direct:fetch-assurance")
        // Extraire les paramètres dynamiques depuis l'URL
            .process(exchange -> {
                // Récupérer les paramètres de l'URL
                String type = exchange.getIn().getHeader("type", String.class);
                String age = exchange.getIn().getHeader("age", String.class);
                String dureePermis = exchange.getIn().getHeader("duree_permis", String.class);

                if (age == null || age.isEmpty() || dureePermis == null || dureePermis.isEmpty() || type == null || type.isEmpty()) {
                    exchange.getIn().setBody("{\"error\": \"Missing required parameters\"}");
                    exchange.getIn().setHeader("CamelHttpResponseCode", 600); // Code de réponse 400 Bad Request
                    exchange.getIn().setHeader("Content-Type", "application/json"); // Définir le type de contenu
                    return;
                }

                // Ajouter les paramètres à l'URL de l'appel vers le service d'assurance
                String url = "http://localhost:8080/assurance?type=" + type + "&age=" + age + "&duree_permis=" + dureePermis + "&bridgeEndpoint=true";
                exchange.getIn().setHeader("CamelHttpUri", url); // Mettre l'URL dynamique dans l'en-tête
            })
            // Appeler le service d'assurance avec l'URL dynamique construite précédemment
            .toD("${header.CamelHttpUri}") // Utiliser l'URL dynamique
            .log("Réponse du service assurance: ${body}");



        from("rest:get:/fetch-lenders")
            .to("http://localhost:8081/lenders-availability?bridgeEndpoint=true") // Appel direct au service de disponibilité des prêteurs avec bridgeEndpoint
            .log("Réponse du service de disponibilité des prêteurs: ${body}")
            .process(exchange -> {
                // Récupérer la réponse du service et la formater en JSON
                String response = exchange.getIn().getBody(String.class);
                exchange.getIn().setHeader("Content-Type", "application/json");
                exchange.getIn().setBody(response);
            });

    }
} 