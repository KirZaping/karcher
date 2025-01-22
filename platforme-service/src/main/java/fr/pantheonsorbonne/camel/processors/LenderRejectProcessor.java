package fr.pantheonsorbonne.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class LenderRejectProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String carId = exchange.getIn().getHeader("carId", String.class);
        io.vertx.core.json.JsonObject jsonResponse = new io.vertx.core.json.JsonObject();
        jsonResponse.put("lender-reject", "The lender reject the location");
        jsonResponse.put("carid", carId);
        exchange.getIn().setHeader("CamelHttpResponseCode", 200);
        exchange.getIn().setHeader("Content-Type", "application/json");
        exchange.getIn().setBody(jsonResponse.toString());
    }
}
