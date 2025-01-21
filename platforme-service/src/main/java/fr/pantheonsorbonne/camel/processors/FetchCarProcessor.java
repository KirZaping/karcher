package fr.pantheonsorbonne.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import io.vertx.core.json.JsonObject;

public class FetchCarProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String task = exchange.getIn().getHeader("task", String.class);
        String location = exchange.getIn().getHeader("location", String.class);
        String startDate = exchange.getIn().getHeader("startDate", String.class);
        String endDate = exchange.getIn().getHeader("endDate", String.class);
        String carId = exchange.getIn().getHeader("carId", String.class);
        
        // Logique de traitement ici
        // Par exemple, vous pouvez créer un message JSON à envoyer
        JsonObject jsonMessage = new JsonObject();
        jsonMessage.put("task", task);
        jsonMessage.put("location", location);
        jsonMessage.put("startDate", startDate);
        jsonMessage.put("endDate", endDate);
        jsonMessage.put("carid", carId);
        
        String message = jsonMessage.toString();
        exchange.getIn().setBody(message);
    }
}
