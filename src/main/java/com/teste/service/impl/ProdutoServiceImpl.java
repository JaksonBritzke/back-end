package com.teste.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.teste.model.Produto;
import com.teste.model.dto.ProdutoDTO;
import com.teste.repository.ProdutoRepository;
import com.teste.service.ProdutoService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ProdutoServiceImpl implements ProdutoService {
    @Inject
    ProdutoRepository repository;

    @Override
    public List<ProdutoDTO> listarTodos() {
        return repository.listAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProdutoDTO buscarPorId(Long id) {
        return toDTO(repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado")));
    }

    @Transactional
    @Override
    public ProdutoDTO salvar(ProdutoDTO dto) {
        Produto produto = toProduto(dto);
        repository.persist(produto);
        return toDTO(produto);
    }

    @Transactional
    @Override
    public ProdutoDTO atualizar(Long id, ProdutoDTO dto) {
        Produto existente = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado"));
        existente.setDescricao(dto.getDescricao());
        existente.setSituacao(dto.getSituacao());
        return toDTO(existente);
    }

    @Transactional
    @Override
    public void deletar(Long id) {
        if (repository.temMovimentacao(id)) {
            throw new BadRequestException("Produto com movimentação não pode ser excluído");
        }
        repository.deleteById(id);
    }

    private ProdutoDTO toDTO(Produto produto) {
        return new ProdutoDTO(
                produto.getCodigo(),
                produto.getDescricao(),
                produto.getSituacao());
    }

    private Produto toProduto(ProdutoDTO dto) {
        return new Produto(
                dto.getCodigo(),
                dto.getDescricao(),
                dto.getSituacao(),
                null);
    }
}