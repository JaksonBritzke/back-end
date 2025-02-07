package com.teste.controller;

import static jakarta.ws.rs.core.Response.Status.CREATED;

import java.util.List;

import com.teste.model.dto.FornecedorDTO;
import com.teste.service.FornecedorService;

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

@Path("/fornecedor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FornecedorController {

    @Inject
    FornecedorService service;

    @GET
    public List<FornecedorDTO> listar() {
        return service.listarTodos();
    }

    @GET
    @Path("/{id}")
    public FornecedorDTO buscarPorId(@PathParam("id") Long id) {
        return service.buscarPorId(id);
    }

    @GET
    @Path("/descricao/{descricao}")
    public FornecedorDTO buscarPorDescricao(@PathParam("descricao") String descricao) {
        return service.buscarPorDescricao(descricao);
    }

    @GET
    @Path("/descricao/like/{descricao}")
    public List<FornecedorDTO> buscarPorDescricaoLike(@PathParam("descricao") String descricao) {
        return service.buscarPorDescricaoLike(descricao);
    }

    @POST
    public Response criar(FornecedorDTO dto) {
        return Response.status(CREATED)
                .entity(service.salvar(dto))
                .build();
    }

    @PUT
    @Path("/{id}")
    public FornecedorDTO atualizar(@PathParam("id") Long id, FornecedorDTO dto) {
        return service.atualizar(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public void excluir(@PathParam("id") Long id) {
        service.deletar(id);
    }
}