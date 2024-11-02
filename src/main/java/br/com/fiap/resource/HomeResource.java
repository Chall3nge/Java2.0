package br.com.fiap.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response.Status;

@Path("/")
public class HomeResource {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response home() {
        String html = """
            <!DOCTYPE html>
            <html>
            <head>
                <title>API FIAP</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        display: flex;
                        justify-content: center;
                        align-items: center;
                        height: 100vh;
                        margin: 0;
                        background-color: #f0f0f0;
                    }
                    .container {
                        text-align: center;
                        padding: 20px;
                        border-radius: 10px;
                        background-color: white;
                        box-shadow: 0 0 10px rgba(0,0,0,0.1);
                    }
                    h1 {
                        color: #E60012;
                        margin-bottom: 10px;
                    }
                    p {
                        color: #666;
                    }
                    .api-path {
                        background-color: #f8f9fa;
                        padding: 10px;
                        border-radius: 5px;
                        margin: 10px 0;
                        color: #E60012;
                        font-family: monospace;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <h1>Bem-vindo a API FIAP</h1>
                    <p>Para acessar a API, use o endpoint:</p>
                    <div class="api-path">/api/usuarios</div>
                    <p>Status: Online ✅</p>
                </div>
            </body>
            </html>
            """;
        return Response.ok(html).build();
    }

    // Tratamento para rotas não encontradas (404)
    @Path("{path : .*}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response notFound() {
        String html = """
            <!DOCTYPE html>
            <html>
            <head>
                <title>404 - Não Encontrado</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        display: flex;
                        justify-content: center;
                        align-items: center;
                        height: 100vh;
                        margin: 0;
                        background-color: #f0f0f0;
                    }
                    .container {
                        text-align: center;
                        padding: 20px;
                        border-radius: 10px;
                        background-color: white;
                        box-shadow: 0 0 10px rgba(0,0,0,0.1);
                    }
                    h1 {
                        color: #E60012;
                        margin-bottom: 10px;
                    }
                    p {
                        color: #666;
                    }
                    .error-code {
                        font-size: 72px;
                        color: #E60012;
                        margin: 20px 0;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="error-code">404</div>
                    <h1>Pagina Nao Encontrada</h1>
                    <p>A rota que voce esta procurando nao existe.</p>
                    <p>Use /api/usuarios para acessar a API.</p>
                </div>
            </body>
            </html>
            """;
        return Response.status(Status.NOT_FOUND).entity(html).build();
    }
}