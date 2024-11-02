package br.com.fiap.resource;

import br.com.fiap.model.Manutencao;
import br.com.fiap.Service.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
//não esta comunicando
@Path("/manutencoes")
public class ManutencaoResource {

    private ManutencaoService manutencaoService = new ManutencaoService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarManutencoes() {
        List<Manutencao> manutencoes = manutencaoService.listarTodas();
        return Response.ok(manutencoes).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarManutencao(Manutencao manutencao) {
        manutencaoService.adicionar(manutencao);
        return Response.status(Response.Status.CREATED).entity(manutencao).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarManutencao(@PathParam("id") int id) {
        Manutencao manutencao = manutencaoService.buscarPorId(id);
        if (manutencao != null) {
            return Response.ok(manutencao).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarManutencao(@PathParam("id") int id, Manutencao manutencao) {
        manutencao.setId(id);
        manutencaoService.atualizar(manutencao);
        return Response.ok(manutencao).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletarManutencao(@PathParam("id") int id) {
        manutencaoService.remover(id);
        return Response.noContent().build();
    }
}
