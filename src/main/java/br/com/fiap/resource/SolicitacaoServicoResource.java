package br.com.fiap.resource;

import br.com.fiap.model.SolicitacaoServico;
import br.com.fiap.Service.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/solicitacoes")
public class SolicitacaoServicoResource {

    private SolicitacaoServicoService solicitacaoService = new SolicitacaoServicoService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarSolicitacoes() {
        List<SolicitacaoServico> solicitacoes = solicitacaoService.listarTodas();
        return Response.ok(solicitacoes).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarSolicitacao(SolicitacaoServico solicitacao) {
        solicitacaoService.adicionar(solicitacao);
        return Response.status(Response.Status.CREATED).entity(solicitacao).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarSolicitacao(@PathParam("id") int id) {
        SolicitacaoServico solicitacao = solicitacaoService.buscarPorId(id);
        if (solicitacao != null) {
            return Response.ok(solicitacao).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarSolicitacao(@PathParam("id") int id, SolicitacaoServico solicitacao) {
        solicitacao.setId(id);
        solicitacaoService.atualizar(solicitacao);
        return Response.ok(solicitacao).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletarSolicitacao(@PathParam("id") int id) {
        solicitacaoService.remover(id);
        return Response.noContent().build();
    }
}
