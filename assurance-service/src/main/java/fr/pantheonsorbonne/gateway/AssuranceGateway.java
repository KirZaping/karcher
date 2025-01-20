package fr.pantheonsorbonne.gateway;

import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import fr.pantheonsorbonne.service.AssuranceService;
import io.vertx.core.json.JsonObject;
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
            .process(exchange -> {
                String jsonMessage = exchange.getIn().getBody(String.class);
                System.out.println("jsonMessage: " + jsonMessage);
                JsonObject jsonObject = new JsonObject(jsonMessage);
                String type = jsonObject.getString("type");
                String age = jsonObject.getString("age");
                String dureePermis = jsonObject.getString("duree_permis");

                if (age == null || age.isEmpty() || dureePermis == null || dureePermis.isEmpty() || type == null || type.isEmpty()) {
                    exchange.getIn().setBody("{\"error\": \"Missing required parameters\"}");
                    exchange.getIn().setHeader("CamelHttpResponseCode", 400); // Code de réponse 400 Bad Request
                    exchange.getIn().setHeader("Content-Type", "application/json"); // Définir le type de contenu
                    return;
                }

                assuranceService.setAssuranceInfo(Double.parseDouble(age), Double.parseDouble(dureePermis), type);
                String response = assuranceService.getAssuranceInfo();
                exchange.getIn().setBody(response);

                exchange.getIn().setHeader("Content-Type", "application/json");
            });
    }
} 
