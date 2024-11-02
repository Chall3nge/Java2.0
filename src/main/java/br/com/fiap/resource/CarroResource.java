package br.com.fiap.resource;

import br.com.fiap.model.Carro;
import br.com.fiap.Service.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/carros")
public class CarroResource {

    private CarroService carroService = new CarroService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarCarros() {
        List<Carro> carros = carroService.listarTodos();
        return Response.ok(carros).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarCarro(Carro carro) {
        carroService.cadastrar(carro);
        return Response.status(Response.Status.CREATED).entity(carro).build();
    }

    @GET
    @Path("/{placa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarCarro(@PathParam("placa") String placa) {
        Carro carro = carroService.buscarPorPlaca(placa);
        if (carro != null) {
            return Response.ok(carro).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarCarro(@PathParam("id") int id, Carro carro) {
        carro.setId(id);
        carroService.atualizar(carro);
        return Response.ok(carro).build();
    }

    @DELETE
    @Path("/{placa}")
    public Response deletarCarro(@PathParam("placa") String placa) {
        carroService.removerCarro(placa);
        return Response.noContent().build();
    }
}
