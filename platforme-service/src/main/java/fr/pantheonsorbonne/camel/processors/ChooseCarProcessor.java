package fr.pantheonsorbonne.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ChooseCarProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String carId = exchange.getIn().getHeader("carId", String.class);
        io.vertx.core.json.JsonObject jsonMessage = new io.vertx.core.json.JsonObject();
        jsonMessage.put("task", "reserve");
        jsonMessage.put("carid", carId);
        
        exchange.getIn().setBody(jsonMessage.toString());
    }
}
