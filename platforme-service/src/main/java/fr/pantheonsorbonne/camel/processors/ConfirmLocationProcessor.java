package fr.pantheonsorbonne.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ConfirmLocationProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        io.vertx.core.json.JsonObject jsonMessage = new io.vertx.core.json.JsonObject();
        jsonMessage.put("task", "choose-car");
        jsonMessage.put("carid", exchange.getIn().getHeader("carId", String.class));
        exchange.getIn().setBody(jsonMessage.toString());
    }
}
