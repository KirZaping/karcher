package fr.pantheonsorbonne.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import io.vertx.core.json.JsonObject;

public class LenderAskingLocation implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String jsonMessage = exchange.getIn().getBody(String.class);
                JsonObject jsonObject = new JsonObject(jsonMessage);
                String status = jsonObject.getString("status");
                String carId = jsonObject.getString("carid");
                JsonObject responseJson = new JsonObject();
                responseJson.put("status", status);
                responseJson.put("carid", carId);
                exchange.getIn().setBody(responseJson.toString());
    }
}
