package br.com.fiap.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/teste")
public class teste {

    @GET
    public Response testeEndpoint() {
        return Response.ok("Endpoint funcionando!").build();
    }
}
