package fr.pantheonsorbonne.gateway;

import org.apache.camel.builder.RouteBuilder;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlateformeGateway extends RouteBuilder{

    @Override
    public void configure() throws Exception {

        //http://localhost:8082/fetch-assurance?type=Arnaque&age=200&duree_permis=5
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
                    exchange.getIn().setHeader("CamelHttpResponseCode", 600); // Code de réponse 400 Bad Request
                    exchange.getIn().setHeader("Content-Type", "application/json"); // Définir le type de contenu
                    return;
                }

                // Créer le message à envoyer au broker
                String message = "{\"type\":\"" + type + "\", \"age\":\"" + age + "\", \"duree_permis\":\"" + dureePermis + "\"}";
                exchange.getIn().setBody(message);
            })
            .to("sjms2:queue:assurance-queue") // Envoyer le message à la queue assurance-queue
            .log("Message envoyé au broker: ${body}");


        //valider la disponibilité d'une voiture
        //http://localhost:8082/fetch-car?task=available&location=Paris&startDate=2025-01-20&endDate=2025-01-21
        from("rest:get:/fetch-car")
            .process(exchange -> {
                String task = exchange.getIn().getHeader("task", String.class);
                String location = exchange.getIn().getHeader("location", String.class);
                String startDate = exchange.getIn().getHeader("startDate", String.class);
                String endDate = exchange.getIn().getHeader("endDate", String.class);
                String message = String.format("{\"task\": \"%s\", \"location\": \"%s\", \"startDate\": \"%s\", \"endDate\": \"%s\"}", 
                    task, location, startDate, endDate);
                exchange.getIn().setBody(message);
            })
            .to("sjms2:queue:cars-queue")
            .log("[PlateformeGateway] Réponse du service de disponibilité des voitures: ${body}");

        //confirmer la location d'une voiture
        from("rest:post:/confirm-location")
            .to("http://localhost:8081/confirm-location?bridgeEndpoint=true")
            .log("Réponse du service de confirmation de la location: ${body}")
            .process(exchange -> {
                String response = exchange.getIn().getBody(String.class);
                exchange.getIn().setHeader("Content-Type", "application/json");
                exchange.getIn().setBody(response);
            });

        from("rest:get:/fetch-all-cars")
            .to("http://localhost:8083/list-cars?bridgeEndpoint=true")
            .log("Réponse du service assurance: ${body}");

        //http://localhost:8082/fetch-availability-cars?carId=1&startDate=2023-11-01&endDate=2023-11-10
        from("rest:get:/fetch-availability-cars")
            .to("http://localhost:8083/list-availability?bridgeEndpoint=true")
            .log("Réponse du service assurance: ${body}");
    }
} 