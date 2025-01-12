// package fr.pantheonsorbonne.route;

// import org.apache.camel.builder.RouteBuilder;

// import jakarta.enterprise.context.ApplicationScoped;

// @ApplicationScoped
// public class LenderRoute extends RouteBuilder {

//     @Override
//     public void configure() throws Exception {
//         from("timer:fetchLenders?period=60000") // Exécute toutes les 60 secondes
//             .to("http://localhost:8081/lenders") // URL de preteur-service
//             .log("Lenders fetched: ${body}")
//             .to("direct:processLenders"); // Envoie les données à un autre endpoint pour traitement

//         from("direct:processLenders")
//             .process(exchange -> {
//                 @SuppressWarnings("unused")
//                 String lendersJson = exchange.getIn().getBody(String.class);
//                 // Traitez le JSON pour l'enregistrer dans la base de données ou l'utiliser
//                 // Vous pouvez utiliser une bibliothèque comme Jackson pour désérialiser le JSON
//                 // Exemple : List<Lender> lenders = objectMapper.readValue(lendersJson, new TypeReference<List<Lender>>() {});
//             });
//     }
// } 