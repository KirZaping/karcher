package fr.pantheonsorbonne.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import fr.pantheonsorbonne.service.LenderService;

public class LenderConfirmation implements Processor {

    private final LenderService lenderService;

    public LenderConfirmation(LenderService lenderService) {
        this.lenderService = lenderService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        String carId = exchange.getIn().getHeader("carId", String.class);
        String startDate = exchange.getIn().getHeader("startDate", String.class);
        String endDate = exchange.getIn().getHeader("endDate", String.class);
        String lenderResponse  = lenderService.lenderConfirmation(Double.parseDouble(carId), startDate, endDate);
        exchange.getIn().setBody(lenderResponse);
    }
}
