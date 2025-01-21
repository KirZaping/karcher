package fr.pantheonsorbonne.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class FetchAssuranceProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        // Récupérer les paramètres de l'URL
        String type = exchange.getIn().getHeader("type", String.class);
        String age = exchange.getIn().getHeader("age", String.class);
        String dureePermis = exchange.getIn().getHeader("duree_permis", String.class);
        System.out.println("type: " + type);
        System.out.println("age: " + age);
        System.out.println("dureePermis: " + dureePermis);

        if (age == null || age.isEmpty() || dureePermis == null || dureePermis.isEmpty() || type == null || type.isEmpty()) {
            io.vertx.core.json.JsonObject errorResponse = new io.vertx.core.json.JsonObject();
            errorResponse.put("error", "Missing required parameters");
            exchange.getIn().setBody(errorResponse.toString());
            exchange.getIn().setHeader("CamelHttpResponseCode", 400); // Code de réponse 400 Bad Request
            exchange.getIn().setHeader("Content-Type", "application/json"); // Définir le type de contenu
            return;
        }

        // Créer le message à envoyer au broker
        io.vertx.core.json.JsonObject jsonMessage = new io.vertx.core.json.JsonObject();
        jsonMessage.put("type", type);
        jsonMessage.put("age", age);
        jsonMessage.put("duree_permis", dureePermis);
        exchange.getIn().setBody(jsonMessage.toString());
    }
}
