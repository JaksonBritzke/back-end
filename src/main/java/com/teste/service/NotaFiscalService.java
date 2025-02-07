package com.teste.service;

import java.util.List;

import com.teste.model.dto.NotaFiscalDTO;

public interface NotaFiscalService {

    NotaFiscalDTO buscarPorNumero(long numero);

    List<NotaFiscalDTO> listarTodas();

    NotaFiscalDTO salvar(NotaFiscalDTO dto);

    NotaFiscalDTO atualizar(NotaFiscalDTO dto);

    void deletar(long numero);
}