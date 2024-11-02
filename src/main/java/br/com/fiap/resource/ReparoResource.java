package br.com.fiap.resource;

import br.com.fiap.model.Reparo;
import br.com.fiap.Service.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/reparos")
public class ReparoResource {

    private ReparoService reparoService = new ReparoService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarReparos() {
        List<Reparo> reparos = reparoService.listarTodas();
        return Response.ok(reparos).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarReparo(Reparo reparo) {
        reparoService.adicionar(reparo);
        return Response.status(Response.Status.CREATED).entity(reparo).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarReparo(@PathParam("id") int id) {
        Reparo reparo = reparoService.buscarPorId(id);
        if (reparo != null) {
            return Response.ok(reparo).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarReparo(@PathParam("id") int id, Reparo reparo) {
        reparo.setId(id);
        reparoService.atualizar(reparo);
        return Response.ok(reparo).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletarReparo(@PathParam("id") int id) {
        reparoService.remover(id);
        return Response.noContent().build();
    }
}
