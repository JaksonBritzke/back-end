package com.teste.service;

import java.util.List;

import com.teste.model.dto.FornecedorDTO;

public interface FornecedorService {
    List<FornecedorDTO> listarTodos();

    FornecedorDTO buscarPorId(Long id);

    FornecedorDTO buscarPorDescricao(String descricao);

    List<FornecedorDTO> buscarPorDescricaoLike(String descricao);

    FornecedorDTO salvar(FornecedorDTO dto);

    FornecedorDTO atualizar(Long id, FornecedorDTO dto);

    void deletar(Long id);
}
