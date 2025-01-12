// package fr.pantheonsorbonne.controller;

// import java.util.List;

// import fr.pantheonsorbonne.model.Lender;
// import fr.pantheonsorbonne.service.LenderService;
// import jakarta.inject.Inject;
// import jakarta.ws.rs.Consumes;
// import jakarta.ws.rs.GET;
// import jakarta.ws.rs.POST;
// import jakarta.ws.rs.Path;
// import jakarta.ws.rs.PathParam;
// import jakarta.ws.rs.Produces;
// import jakarta.ws.rs.core.MediaType;
// import jakarta.ws.rs.core.Response;

// @Path("/lenders")
// public class LenderController {

//     @Inject
//     LenderService lenderService;

//     @POST
//     @Consumes(MediaType.APPLICATION_JSON)
//     @Produces(MediaType.APPLICATION_JSON)
//     public Response addLender(Lender lender) {
//         lenderService.addLender(lender);
//         return Response.ok(lender).build();
//     }

//     @GET
//     @Produces(MediaType.APPLICATION_JSON)
//     public List<Lender> getAllLenders() {
//         return lenderService.listLenders();
//     }

//     @GET
//     @Path("/{id}")
//     @Produces(MediaType.APPLICATION_JSON)
//     public Response getLenderById(@PathParam("id") Long id) {
//         Lender lender = lenderService.findLenderById(id);
//         if (lender != null) {
//             return Response.ok(lender).build();
//         } else {
//             return Response.status(Response.Status.NOT_FOUND).entity("Lender not found").build();
//         }
//     }
// } 

package fr.pantheonsorbonne.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/lenders")
public class LenderController {

    @GET
    public String getLendersInfos() {
        return "{\"info\": \"Information sur les preteurs\"}";
    }
} 