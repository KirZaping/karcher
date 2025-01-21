package fr.pantheonsorbonne.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import fr.pantheonsorbonne.service.PlatformService;
import io.vertx.core.json.JsonObject;
import jakarta.inject.Inject;

public class AssuranceSelectedProcessor implements Processor {

    @Inject
    PlatformService platformService;

    public AssuranceSelectedProcessor(PlatformService platformService) {
        this.platformService = platformService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Double priceAssurance = platformService.getPriceAssurance();
        if (priceAssurance == null) {
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.put("message", "Veuillez d'abord choisir une assurance.");
            exchange.getIn().setBody(jsonResponse.toString());
            exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 400);
        } else {
            JsonObject jsonResponse = new JsonObject().put("message", "Assurance choisie.");
            exchange.getIn().setBody(jsonResponse.toString());
            exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 200);
        }
    }
}
