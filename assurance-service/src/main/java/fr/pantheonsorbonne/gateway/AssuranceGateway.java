package fr.pantheonsorbonne.gateway;

import org.apache.camel.builder.RouteBuilder;

import fr.pantheonsorbonne.service.AssuranceService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AssuranceGateway extends RouteBuilder {

    @Inject
    AssuranceService assuranceService;

    @Override
    public void configure() throws Exception {

        from("rest:get:/assurance/fetch-assurance")
            .to("direct:getAssuranceInfo");
            

        from("direct:getAssuranceInfo")
            .process(exchange -> {
                // Récupérer les paramètres de la requête
                String type = exchange.getIn().getHeader("type", String.class);
                String ageHeader = exchange.getIn().getHeader("age", String.class);
                String dureePermisHeader = exchange.getIn().getHeader("duree_permis", String.class);

                // Vérifier si les en-têtes sont présents et non vides
                if (ageHeader == null || ageHeader.isEmpty() || dureePermisHeader == null || dureePermisHeader.isEmpty() || type == null || type.isEmpty()) {
                    exchange.getIn().setBody("{\"error\": \"Missing required parameters\"}");
                    exchange.getIn().setHeader("CamelHttpResponseCode", 400); // Code de réponse 400 Bad Request
                    exchange.getIn().setHeader("Content-Type", "application/json"); // Définir le type de contenu
                    return;
                }

                // Conversion des paramètres
                double age = Double.parseDouble(ageHeader);
                double dureePermis = Double.parseDouble(dureePermisHeader);

                // Appeler le service pour obtenir les informations
                assuranceService.setAssuranceInfo(age, dureePermis, type);
                String response = assuranceService.getAssuranceInfo();

                // Retourner la réponse du service
                exchange.getIn().setBody(response);
                exchange.getIn().setHeader("Content-Type", "application/json"); // Définir le type de contenu
            })
            .log("Réponse du service assurance: ${body}");
    }
} 