package fr.pantheonsorbonne.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import io.vertx.core.json.JsonObject;
import fr.pantheonsorbonne.service.AssuranceService;

public class AssuranceManagerProcessor implements Processor {

    private final AssuranceService assuranceService;

    public AssuranceManagerProcessor(AssuranceService assuranceService) {
        this.assuranceService = assuranceService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        if (assuranceService == null) {
            JsonObject errorResponse = new JsonObject();
            errorResponse.put("error", "AssuranceService is not initialized");
            exchange.getIn().setBody(errorResponse.toString());
            exchange.getIn().setHeader("CamelHttpResponseCode", 500); // Code de réponse 500 Internal Server Error
            exchange.getIn().setHeader("Content-Type", "application/json");
            return;
        }

        String jsonMessage = exchange.getIn().getBody(String.class);
        System.out.println("jsonMessage: " + jsonMessage);
        JsonObject jsonObject = new JsonObject(jsonMessage);
        String type = jsonObject.getString("type");
        String age = jsonObject.getString("age");
        String dureePermis = jsonObject.getString("duree_permis");
        if (age == null || age.isEmpty() || dureePermis == null || dureePermis.isEmpty() || type == null || type.isEmpty()) {
            JsonObject errorResponse = new JsonObject();
            errorResponse.put("error", "Missing required parameters");
            exchange.getIn().setBody(errorResponse.toString());
            exchange.getIn().setHeader("CamelHttpResponseCode", 400); // Code de réponse 400 Bad Request
            exchange.getIn().setHeader("Content-Type", "application/json"); // Définir le type de contenu
            return;
        }

        assuranceService.setAssuranceInfo(Double.parseDouble(age), Double.parseDouble(dureePermis), type);
        String response = assuranceService.getAssuranceInfo();
        exchange.getIn().setBody(response);

        exchange.getIn().setHeader("Content-Type", "application/json");
    }
}
