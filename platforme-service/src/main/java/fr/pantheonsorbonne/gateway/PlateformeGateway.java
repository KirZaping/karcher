package fr.pantheonsorbonne.gateway;

import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlateformeGateway extends RouteBuilder{

    @ConfigProperty(name = "quarkus.artemis.queue.platformeQueue")
    String platformeQueue;

    @ConfigProperty(name = "quarkus.artemis.queue.assuranceQueue")
    String assuranceQueue;

    @ConfigProperty(name = "quarkus.artemis.queue.carsQueue")
    String carsQueue;

    @ConfigProperty(name = "quarkus.artemis.queue.lenderQueue")
    String lenderQueue;

    @Override
    public void configure() throws Exception {
        // Configuration du nom d'hôte pour les endpoints REST
        restConfiguration()
            .host("localhost")
            .port(24000);

        // http://localhost:24000/fetch-assurance?type=Arnaque&age=200&duree_permis=5
        from("rest:get:/fetch-assurance")
            .process(exchange -> {
                // Récupérer les paramètres de l'URL
                String type = exchange.getIn().getHeader("type", String.class);
                String age = exchange.getIn().getHeader("age", String.class);
                String dureePermis = exchange.getIn().getHeader("duree_permis", String.class);
                System.out.println("type: " + type);
                System.out.println("age: " + age);
                System.out.println("dureePermis: " + dureePermis);

                if (age == null || age.isEmpty() || dureePermis == null || dureePermis.isEmpty() || type == null || type.isEmpty()) {
                    exchange.getIn().setBody("{\"error\": \"Missing required parameters\"}");
                    exchange.getIn().setHeader("CamelHttpResponseCode", 400); // Code de réponse 400 Bad Request
                    exchange.getIn().setHeader("Content-Type", "application/json"); // Définir le type de contenu
                    return;
                }

                // Créer le message à envoyer au broker
                String message = "{\"type\":\"" + type + "\", \"age\":\"" + age + "\", \"duree_permis\":\"" + dureePermis + "\"}";
                exchange.getIn().setBody(message);
            })
            .to(assuranceQueue);

        // http://localhost:24000/choose-car?task=confirm-location?carId=4
        from("rest:get:/choose-car")
            .log("[PlateformeGateway] Message en cours de traitement")
            .process(exchange -> {
                String carId = exchange.getIn().getHeader("carId", String.class);
                String message = "{\"task\": \"choose-car\", \"carid\": \"" + carId + "\"}";
                exchange.getIn().setBody(message);
            })
            .to(carsQueue);


        //valider la disponibilité d'une voiture
        // http://localhost:24000/fetch-car?task=available&location=Paris&startDate=2025-01-24&endDate=2025-01-27
        from("rest:get:/fetch-car")
            .log("[PlateformeGateway] Message en cours de traitement")
            .process(exchange -> {
                String task = exchange.getIn().getHeader("task", String.class);
                String location = exchange.getIn().getHeader("location", String.class);
                String startDate = exchange.getIn().getHeader("startDate", String.class);
                String endDate = exchange.getIn().getHeader("endDate", String.class);
                String carId = exchange.getIn().getHeader("carId", String.class);
                String message = String.format("{\"task\": \"%s\", \"location\": \"%s\", \"startDate\": \"%s\", \"endDate\": \"%s\", \"carid\": \"%s\"}", 
                    task, location, startDate, endDate, carId);
                exchange.getIn().setBody(message);
            })
            .to(carsQueue);

        //confirmer la location d'une voiture
        // http://localhost:24000/confirm-location?carId=4
        from("rest:get:/confirm-location")
            .log("[PlateformeGateway] Message en cours de traitement")
            .process(exchange -> {
                String carId = exchange.getIn().getHeader("carId", String.class);
                String message = "{\"carid\": \"" + carId + "\"}";
                exchange.getIn().setBody(message);
            })
            .to(lenderQueue)
            .process(exchange -> {
                String jsonMessage = exchange.getIn().getBody(String.class);
                JsonObject jsonObject = new JsonObject(jsonMessage);
                String status = jsonObject.getString("status");
                exchange.getIn().setBody("{\"status\": \"" + status + "\"}");
            })
            .choice()
                .when().simple("${body} contains 'accept'")
                    .to("direct:chooseCar")
                .otherwise()
                    .to("direct:lender-reject");
            
        from("direct:chooseCar")
            .process(exchange -> {
                //TODO: récupérer le carId et envoyer le message au service de location
                String message = "{\"task\": \"reserve\", \"carid\": \"4\"}";

                exchange.getIn().setBody(message);
            })
            .to(carsQueue);
    
        from("direct:lender-reject")
            .process(exchange -> {
                String message = "{\"lender-reject\": \"The lender reject the location\"}";
                exchange.getIn().setHeader("CamelHttpResponseCode", 200);
                exchange.getIn().setHeader("Content-Type", "application/json");
                exchange.getIn().setBody(message);
            });
    }
} 
