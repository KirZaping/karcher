package fr.pantheonsorbonne.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import fr.pantheonsorbonne.service.PlatformService;
import io.vertx.core.json.JsonObject;
import jakarta.inject.Inject;

public class SetAssurancePriceProcessor implements Processor {

    @Inject
    PlatformService platformService;

    public SetAssurancePriceProcessor(PlatformService platformService) {
        this.platformService = platformService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        String response_price = exchange.getIn().getBody(String.class);
        System.out.println(response_price);

        try {
            JsonObject jsonResponse = new JsonObject(response_price);
            Double price = jsonResponse.getDouble("prix");
            System.out.println("price: " + price);
            platformService.setPriceAssurance(price);
        } catch (Exception e) {
            System.err.println("Erreur lors du parsing du JSON: " + e.getMessage());
            exchange.getIn().setBody("Erreur de format JSON");
            exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 400);
        }
    }
}
