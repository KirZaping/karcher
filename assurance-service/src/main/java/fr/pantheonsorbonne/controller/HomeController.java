package fr.pantheonsorbonne.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Path("/")
public class HomeController {

    @GET
    @Produces("text/html") // Indique que le type de contenu est HTML
    public Response home() {
        try (InputStream inputStream = getClass().getResourceAsStream("/META-INF/resources/templates/index.html")) {
            if (inputStream == null) {
                return Response.serverError().entity("Fichier index.html introuvable").build();
            }
            String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            return Response.ok(content).build(); // Renvoie le contenu du fichier index.html
        } catch (IOException e) {
            return Response.serverError().entity("Erreur lors de la lecture de la page d'accueil").build();
        }
    }
}