package fr.pantheonsorbonne.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import jakarta.inject.Inject;
import fr.pantheonsorbonne.service.LenderService;

public class LenderConfirmation implements Processor {

    @Inject
    LenderService lenderService;

    @Override
    public void process(Exchange exchange) throws Exception {
        String carId = exchange.getIn().getHeader("carId", String.class);
        String lenderResponse  = lenderService.lenderConfirmation(Double.parseDouble(carId));
        exchange.getIn().setBody(lenderResponse);
    }
}
