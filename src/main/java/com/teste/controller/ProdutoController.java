package com.teste.controller;

import static jakarta.ws.rs.core.Response.Status.CREATED;

import java.util.List;

import com.teste.model.Produto;
import com.teste.model.dto.ProdutoDTO;
import com.teste.service.ProdutoService;

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

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoController {
    
    @Inject
    ProdutoService service;

    @GET
    public List<ProdutoDTO> listar() {
        return service.listarTodos();
    }

    @GET
    @Path("/{id}")
    public ProdutoDTO buscar(@PathParam("id") Long id) {
        return service.buscarPorId(id);
    }

    @POST
    public Response criar(ProdutoDTO dto) {
       return Response.status(CREATED)
               .entity(service.salvar(dto))
               .build();
    }
    
    @PUT
    @Path("/{id}")
    public ProdutoDTO atualizar(@PathParam("id") Long id, ProdutoDTO dto) {
       return service.atualizar(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public void excluir(@PathParam("id") Long id) {
        service.deletar(id);
    }
}