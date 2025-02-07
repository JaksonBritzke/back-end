package com.teste.controller;

import static jakarta.ws.rs.core.Response.Status.CREATED;

import java.util.List;

import com.teste.model.dto.NotaFiscalDTO;
import com.teste.service.NotaFiscalService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/notas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NotaFiscalController {
    @Inject
    NotaFiscalService service;

    @POST
    public Response criar(NotaFiscalDTO dto) {
        return Response.status(CREATED)
                .entity(service.salvar(dto))
                .build();
    }

    @PUT
    public NotaFiscalDTO atualizar(NotaFiscalDTO dto) {
        return service.atualizar(dto);
    }

    @DELETE
    @Path("/{numero}")
    public void excluir(@PathParam("numero") long numero) {
        service.deletar(numero);
    }

    @GET
    public List<NotaFiscalDTO> listar() {
        return service.listarTodas();
    }

}