package com.teste.service;

import java.util.List;

import com.teste.model.dto.ProdutoDTO;

public interface ProdutoService {
    List<ProdutoDTO> listarTodos();

    ProdutoDTO buscarPorId(Long id);

    ProdutoDTO salvar(ProdutoDTO dto);

    ProdutoDTO atualizar(Long id, ProdutoDTO dto);

    void deletar(Long id);
}
